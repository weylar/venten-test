package com.idris.aminu.android.models


typealias Filter = ArrayList<FilterElement>


data class FilterElement (
    val id: Long,
    val start_year: Long,
    val end_year: Long,
    val gender: String,
    val countries: List<String>,
    val colors: List<String>
)