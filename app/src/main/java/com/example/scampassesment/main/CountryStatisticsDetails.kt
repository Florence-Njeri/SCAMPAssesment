package com.example.scampassesment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.scampassesment.R

class CountryStatisticsDetails : Fragment() {

    companion object {
        fun newInstance() = CountryStatisticsDetails()
    }

    private lateinit var viewModel: CountryStatisticsDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.country_statistics_details_fragment, container, false)

        val countryProperty =
            CountryStatisticsDetailsArgs.fromBundle(requireArguments()).selectedCountry
        Log.d("CountryProperty", countryProperty.toString())

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryStatisticsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
