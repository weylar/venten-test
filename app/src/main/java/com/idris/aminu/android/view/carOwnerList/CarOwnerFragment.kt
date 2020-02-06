package com.idris.aminu.android.view.carOwnerList

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
import com.idris.aminu.android.databinding.FragmentCarOwnerListBinding
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.view.filterList.FilterClickListener
import com.idris.aminu.android.view.filterList.FilterListAdapter
import com.idris.aminu.android.viewModel.filter.FilterListViewModelFactory
import com.idris.aminu.android.viewModel.owner.CarOwnerViewModel
import com.idris.aminu.android.viewModel.owner.CarOwnerViewModelFactory
import timber.log.Timber


class CarOwnerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCarOwnerListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_car_owner_list, container, false
        )

        val args = CarOwnerFragmentArgs.fromBundle(arguments!!).car
        val viewModelFactory =
            CarOwnerViewModelFactory(args)
        val carOwnerViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(CarOwnerViewModel::class.java)
        }
        binding.carOwnerViewModel = carOwnerViewModel
        binding.lifecycleOwner = this

        val adapter = CarOwnerAdapter()
        binding.carownerRecycler.adapter = adapter

        carOwnerViewModel.filterResult.observe(this, Observer {filteredResult ->
            adapter.submitListOnCall(filteredResult)
            binding.animationView.visibility = View.GONE
        })

        binding.close.setOnClickListener { findNavController().navigateUp() }
        return binding.root
    }

}