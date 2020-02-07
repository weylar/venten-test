package com.idris.aminu.android.view.carOwnerList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idris.aminu.android.databinding.CarOwnerListViewBinding
import com.idris.aminu.android.models.CarOwner
import com.idris.aminu.android.models.CarOwnerList


class CarOwnerAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(CarOwnerDiffCallback()) {


    fun submitListOnCall(list: CarOwnerList) {

        submitList(list.map { DataItem.CarOwnerItem(it) })

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val ownerItems = getItem(position) as DataItem.CarOwnerItem
                holder.bind(ownerItems.filter)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(private val binding: CarOwnerListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CarOwner) {
            binding.carOwner = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CarOwnerListViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class CarOwnerDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}


sealed class DataItem {

    data class CarOwnerItem(val filter: CarOwner) : DataItem() {
        override val id = filter.id
    }

    abstract val id: Long
}
