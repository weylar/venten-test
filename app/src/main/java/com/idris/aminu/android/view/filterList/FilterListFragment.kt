package com.idris.aminu.android.view.filterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.idris.aminu.android.R
import com.idris.aminu.android.databinding.FragmentFilterListBinding


class FilterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentFilterListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_filter_list, container, false
        )

        val filterListViewModel = ViewModelProvider(this).get(FilterListViewModel::class.java)
        filterListViewModel.filterList

        binding.filterListViewModel = filterListViewModel

        binding.lifecycleOwner = this


        val adapter = FilterListAdapter(FilterClickListener {
            filterListViewModel.onFilterClicked(it)
        })
        binding.filterRecycler.adapter = adapter

        filterListViewModel.filterList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitListOnCall(it)
            }
        })

        filterListViewModel.navigateFilteredOwners.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
//                this.findNavController().navigate(
//                    SleepTrackerFragmentDirections
//                        .actionSleepTrackerFragmentToSleepDetailFragment(id))
              //  sleepTrackerViewModel.onSleepDataQualityNavigated()
            }
        })





        return binding.root
    }

}