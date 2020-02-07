package com.idris.aminu.android.network

import android.content.Context
import android.net.ConnectivityManager
import com.idris.aminu.android.util.Utility
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Utility.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun isNetworkAvailable(context: Context): Boolean? {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                return true
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                return true
            }
        } else {
            return false
        }
        return false
    }

}