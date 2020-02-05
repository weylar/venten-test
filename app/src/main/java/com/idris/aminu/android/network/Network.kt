package com.idris.aminu.android.network

import com.idris.aminu.android.util.Utility
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object Network {

     val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Utility.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}