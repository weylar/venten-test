package com.idris.aminu.android.models


import kotlinx.serialization.*

typealias Filter = ArrayList<FilterElement>

@Serializable
data class FilterElement (
    val id: Long,

    @SerialName("start_year")
    val startYear: Long,

    @SerialName("end_year")
    val endYear: Long,

    val gender: String,
    val countries: List<String>,
    val colors: List<String>
)