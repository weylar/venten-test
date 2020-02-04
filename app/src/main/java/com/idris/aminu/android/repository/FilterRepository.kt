package com.idris.aminu.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idris.aminu.android.data.FilterAPI
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class FilterRepository {


    fun getFilterList(): MutableLiveData<Filter> {
        val filter = MutableLiveData<Filter>()
        val retrofit = Network.retrofit.create(FilterAPI::class.java)
        val call: Call<Filter> = retrofit.getFilterList()
        call.enqueue(object : Callback<Filter> {
            override fun onFailure(call: Call<Filter>, t: Throwable) {
                Timber.e(t.message)
                filter.value = null

            }

            override fun onResponse(call: Call<Filter>, response: Response<Filter>) {
                if (response.isSuccessful) {
                    Timber.i("Filter successfully retrieved")
                    filter.value = response.body()
                }
            }
        })
        return filter

    }


}