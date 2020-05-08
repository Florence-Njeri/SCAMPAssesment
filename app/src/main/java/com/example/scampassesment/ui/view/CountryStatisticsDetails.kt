package com.example.scampassesment.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.scampassesment.R
import com.example.scampassesment.databinding.CountryStatisticsDetailsFragmentBinding
import com.example.scampassesment.ui.CountryStatisticsDetailsArgs
import com.example.scampassesment.ui.viewModel.CountryStatisticsDetailsViewModel
import com.example.scampassesment.ui.viewModel.DetailsViewModelFactory

class CountryStatisticsDetails : Fragment() {

    companion object {
        fun newInstance() =
            CountryStatisticsDetails()
    }

    //    private lateinit var viewModel: CountryStatisticsDetailsViewModel
    private lateinit var binding: CountryStatisticsDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.country_statistics_details_fragment,
            container,
            false
        )
        val application = requireNotNull(activity).application

        val countryProperty =
            CountryStatisticsDetailsArgs.fromBundle(
                requireArguments()
            ).selectedCountry
        Log.d("CountryProperty", countryProperty.toString())

        val viewModelFactory =
            DetailsViewModelFactory(
                countryProperty,
                application
            )
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(CountryStatisticsDetailsViewModel::class.java)

        return binding.root
    }

}
