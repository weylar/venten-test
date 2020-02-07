package com.idris.aminu.android.data

import com.idris.aminu.android.models.Filter
import retrofit2.Call
import retrofit2.http.GET

interface FilterAPI {


    @GET("assessment/filter.json")
    fun getFilterList(): Call<Filter>
}