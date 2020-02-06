package com.idris.aminu.android.view.filterList


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.idris.aminu.android.R
import com.idris.aminu.android.databinding.FragmentFilterListBinding
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.viewModel.filter.FilterListViewModel
import com.idris.aminu.android.viewModel.filter.FilterListViewModelFactory
import timber.log.Timber


private const val MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1

class FilterListFragment : Fragment() {


    private val viewModelFactory = FilterListViewModelFactory(FilterRepository())
    private val filterListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FilterListViewModel::class.java)
    }
    private lateinit var loadingFrag: DialogProgress

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

        val adapter = FilterListAdapter(FilterClickListener {
            val action = FilterListFragmentDirections.actionFilterFragmentToCarOwnerFragment(it)
            this.findNavController().navigate(action)
        })
        binding.filterRecycler.adapter = adapter

        filterListViewModel.filterList.observe(this, Observer { filterList ->
            filterList?.let {
                filterListViewModel.grantAccess.observe(this, Observer {
                    if (it){
                        adapter.submitListOnCall(filterList)
                        binding.animationView.visibility = View.GONE
                    }
                })

            }
        })


        checkPermissionAndStart()
        val config = PRDownloaderConfig.newBuilder().setDatabaseEnabled(true).build()
        PRDownloader.initialize(context, config)
        loadingFrag = DialogProgress(context!!)
        filterListViewModel.startDialogDownload.observe(this, Observer {
            if (!it) {
                loadingFrag.showDialog()
            }
        })

        filterListViewModel.completeDownload.observe(this, Observer {
            if (it) loadingFrag.dismiss()
        })




        return binding.root
    }

    private fun checkPermissionAndStart() {
        if (ContextCompat.checkSelfPermission(context!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            promptDialogPermission()

        } else {
            filterListViewModel.checkDataExist()
            filterListViewModel.grantAccess.value = true
        }


    }

    private fun promptDialogPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_WRITE_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_STORAGE -> {
                if ((grantResults.isNotEmpty() && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        filterListViewModel.checkDataExist()
                        filterListViewModel.grantAccess.value = true
                    }
                } else {
                    promptDialogPermission()
                }

            }


        }
    }
}