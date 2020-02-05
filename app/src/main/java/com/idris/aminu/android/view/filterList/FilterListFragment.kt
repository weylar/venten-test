package com.idris.aminu.android.view.filterList

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.idris.aminu.android.R
import com.idris.aminu.android.databinding.FragmentFilterListBinding
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.util.Utility.CAR_OWNER_DATA
import com.idris.aminu.android.util.Utility.DOWNLOAD_URL
import com.idris.aminu.android.util.Utility.FOLDER
import com.idris.aminu.android.viewModel.filter.FilterListViewModel
import com.idris.aminu.android.viewModel.filter.FilterListViewModelFactory
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream


class FilterListFragment : Fragment() {

    private val viewModelFactory =
        FilterListViewModelFactory(
            FilterRepository(),
            context!!
        )
    private val filterListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FilterListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFilterListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_filter_list, container, false
        )

        binding.filterListViewModel = filterListViewModel
        binding.lifecycleOwner = this

        val adapter =
            FilterListAdapter(FilterClickListener { filterListViewModel.onFilterClicked(it) })
        binding.filterRecycler.adapter = adapter

        filterListViewModel.filterList.observe(this, Observer { filterList ->
            filterList?.let {
                Timber.i("")
                adapter.submitListOnCall(filterList)
                binding.animationView.visibility = View.GONE
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


        filterListViewModel.startDialogDownload.observe(this, Observer {

        })




        return binding.root
    }





}