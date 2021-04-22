package com.rba.currency.exchange.country

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rba.currency.domain.model.CountryModel
import com.rba.currency.exchange.databinding.ItemCountryBinding

class CountryAdapter(
    private val itemClick: (model: CountryModel) -> Unit
) : ListAdapter<CountryModel, CountryAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        holder.itemView.setOnClickListener {
            itemClick(model)
        }
    }

    class ItemViewHolder(
        private val binding: ItemCountryBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CountryModel) {
            binding.titleTextView.text = model.name
            binding.infoTextView.text = model.info

            Glide.with(context)
                .load(model.image)
                .into(binding.countryFlagImageView)
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCountryBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding, parent.context)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CountryModel>() {
        override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem.value == newItem.value
        }
    }

}