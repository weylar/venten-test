
package com.idris.aminu.android.view.filterList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idris.aminu.android.databinding.FilterListViewBinding
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.models.FilterElement


class FilterListAdapter(private val clickListener: FilterClickListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(FilterDiffCallback()) {


    fun submitListOnCall(list: Filter) {

        submitList(list.map { DataItem.FilterElementItem(it) })

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val filterItem = getItem(position) as DataItem.FilterElementItem
                holder.bind(clickListener, filterItem.filter)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(private val binding: FilterListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: FilterClickListener, item: FilterElement) {
            binding.filter = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilterListViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class FilterDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class FilterClickListener(val clickListener: (filter: FilterElement) -> Unit) {
    fun onClick(filter: FilterElement) = clickListener(filter)
}

sealed class DataItem {

    data class FilterElementItem(val filter: FilterElement) : DataItem() {
        override val id = filter.id
    }

    abstract val id: Long
}
