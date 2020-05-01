package com.example.scampassesment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scampassesment.databinding.CountriesListViewBinding
import com.example.scampassesment.model.Country

class CountriesStatisticsAdapter(val clickListener: ClickListener) :
    ListAdapter<Country, RecyclerView.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticssViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return StatisticssViewHolder(CountriesListViewBinding.inflate(layoutInflater))


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StatisticssViewHolder -> {
                val activeProjects: Country = getItem(position)
                //On click navigate

                holder.itemView.setOnClickListener {
                    clickListener.onClick(activeProjects)
                }
                holder.bind(activeProjects, clickListener)
            }
        }
    }


    class StatisticssViewHolder(private var binding: CountriesListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(countries: Country, clickListener: ClickListener) {

            binding.country.text = countries.Country
            binding.statistics.text = countries.TotalConfirmed.toString()


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