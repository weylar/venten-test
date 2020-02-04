/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.idris.aminu.android.view.filterList


import androidx.lifecycle.*
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.repository.FilterRepository


class FilterListViewModel(private val repository: FilterRepository) : ViewModel() {


    private var _filterList = MutableLiveData<FilterElement>()
    val filterList
        get() = _filterList

    private val _navigateFilteredOwners = MutableLiveData<Long>()
    val navigateFilteredOwners
        get() = _navigateFilteredOwners


    init {
        initializeFetch()
    }

    private fun initializeFetch() {
        _filterList.let { repository.getFilterList() }
    }

    fun onFilterClicked(id: Long) {
        _navigateFilteredOwners.value = id
    }


    override fun onCleared() {
        super.onCleared()

    }
}
