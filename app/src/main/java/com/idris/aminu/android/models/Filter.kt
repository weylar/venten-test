package com.idris.aminu.android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


typealias Filter = ArrayList<FilterElement>

@Parcelize
data class FilterElement(
    val id: Long,
    val start_year: Long,
    val end_year: Long,
    val gender: String,
    val countries: List<String>,
    val colors: List<String>
) : Parcelable