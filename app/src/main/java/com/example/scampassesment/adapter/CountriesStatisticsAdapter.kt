package com.example.scampassesment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scampassesment.R
import com.example.scampassesment.model.Countries
import com.example.scampassesment.model.Country
import com.example.scampassesment.model.Summary

class CountriesStatisticsAdapter(val clickListener: ClickListener) :
    ListAdapter<Country, RecyclerView.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestEventsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return LatestEventsViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LatestEventsViewHolder -> {
                val activeProjects: Country = getItem(position)
                //On click navigate

                holder.itemView.setOnClickListener {
                    clickListener.onClick(activeProjects)
                }
                holder.bind(activeProjects, clickListener)
            }
        }
    }


    class LatestEventsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.countries_list_view, parent, false)) {
        private var mCountries: TextView? = null
        private var mStatistics: TextView? = null

        init {
            mCountries = itemView.findViewById(R.id.country)
            mStatistics = itemView.findViewById(R.id.statistics)


        }

        fun bind(countries: Country, clickListener: ClickListener) {

            mCountries?.text = countries.Country
            mStatistics?.text = countries.TotalConfirmed.toString()


        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.Date == newItem.Date
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

}

class ClickListener(val clickListener: (countries: Country) -> Unit) {
    fun onClick(countries: Country) = clickListener(countries)

}