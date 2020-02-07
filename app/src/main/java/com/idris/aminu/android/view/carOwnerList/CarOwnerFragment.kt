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
import com.google.android.material.snackbar.Snackbar
import com.idris.aminu.android.R
import com.idris.aminu.android.databinding.FragmentCarOwnerListBinding
import com.idris.aminu.android.viewModel.owner.CarOwnerViewModel
import com.idris.aminu.android.viewModel.owner.CarOwnerViewModelFactory


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

        //Pretty animation to display while searching through file
        carOwnerViewModel.filterResult.observe(this, Observer { filteredResult ->
            adapter.submitListOnCall(filteredResult)
            binding.animationView.visibility = View.GONE
            if (filteredResult.size < 1) {
                binding.noRecordMatch.visibility = View.VISIBLE
            } else {
                Snackbar.make(
                    binding.close,
                    "${filteredResult.size} total records fetched!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        //View to show if db is available or not. If not re-download
        carOwnerViewModel.isDbAvailable.observe(this, Observer {
            if (!it) {
                binding.animationView.visibility = View.GONE
                binding.animationViewEmpty.visibility = View.VISIBLE

            }
        })

        binding.close.setOnClickListener { findNavController().navigateUp() }
        return binding.root
    }

}