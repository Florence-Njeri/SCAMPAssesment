package com.example.scampassesment.main

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.scampassesment.R
import com.example.scampassesment.adapter.CountriesStatisticsAdapter
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.Summary
import kotlinx.android.synthetic.main.countries_search_bar.view.*
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {


    val worldSummaryCallback = object : Callback<Summary> {
        override fun onFailure(call: Call<Summary>?, t: Throwable?) {
            Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
        }

        override fun onResponse(call: Call<Summary>, response: Response<Summary>) {
            response.isSuccessful.let {
                Log.d("WorldSummary:", response.body().toString())
//                val resultList = response.body()
//                Log.d("WorldSummarySize:", resultList?.Countries?.size .toString())
//                Log.d("WorldSummarySizeResult:", mutableListOf(resultList).size .toString())
//
//                var adapter = CountriesStatisticsAdapter(ClickListener {
//
//                })
//                countriesList.adapter=adapter
//                adapter.submitList(resultList?.Countries)

            }
        }

    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mAdapter: CountriesStatisticsAdapter
    var countryList = ArrayList<Country>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(activity).application
        viewModelFactory = MainViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        if (isNetworkConnected()) {

//            CoronavirusStatisticsRetriever.getWorldSummary(worldSummaryCallback)


        } else {
            AlertDialog.Builder(this.requireContext()).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        mAdapter = CountriesStatisticsAdapter(CountriesStatisticsAdapter.ClickListener {

//            viewModel.displayPropertyDetails(it)
        }, countryList)
        countriesList.adapter = mAdapter

        viewModel.statistics.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
            countryList.addAll(it)

        })

        val sharedPreference =
            context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        totalCasesText.text = sharedPreference?.getString("totalConfirmed", " ")
        recoveredCasesText.text = sharedPreference?.getString("totalRecovered", " ")
        totalDeathsText.text = sharedPreference?.getString("totalDeaths", " ")

        include2.search_hint.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
        })

        include2.search_icon.setOnClickListener {
            Utils.hideSoftKeyBoard(requireContext(), it)
        }

        viewModel.navigateToSelectedCountry.observe(viewLifecycleOwner, Observer {
            if (null != it) {

                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToCountryStatisticsDetails(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Country> = ArrayList()
        for (item in countryList) {
            if (item.Country.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        mAdapter.filterList(filteredList)
        mAdapter.submitList(filteredList)
    }
    //Check the users network connectivity
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager =
            activity?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


}

object Utils {

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }
}