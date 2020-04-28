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

class CountriesStatisticsAdapter(val clickListener: ClickListener) :
    ListAdapter<Countries, RecyclerView.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestEventsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return LatestEventsViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LatestEventsViewHolder -> {
                val activeProjects: Countries = getItem(position)
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

        init {
            mCountries = itemView.findViewById(R.id.country)


        }

        fun bind(countries: Countries, clickListener: ClickListener) {

            mCountries?.text = countries.Country


        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Countries>() {

        override fun areItemsTheSame(oldItem: Countries, newItem: Countries): Boolean {
            return oldItem.Country == newItem.Country
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Countries, newItem: Countries): Boolean {
            return oldItem == newItem
        }
    }

}

class ClickListener(val clickListener: (countries: Countries) -> Unit) {
    fun onClick(countries: Countries) = clickListener(countries)

}