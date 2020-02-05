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

package com.idris.aminu.android.viewModel.filter


import android.content.Context
import android.os.Environment
import androidx.lifecycle.*
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.util.Utility
import com.idris.aminu.android.util.Utility.CAR_OWNER_DATA
import timber.log.Timber
import java.io.File


class FilterListViewModel(private val repository: FilterRepository, private val context: Context) :
    ViewModel() {


    private val file by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER
        )
    }

    private var _filterList = MutableLiveData<Filter>()
    val filterList
        get() = _filterList

    private val _navigateFilteredOwners = MutableLiveData<Long>()
    val navigateFilteredOwners
        get() = _navigateFilteredOwners

    private val _startDialogDownload = MutableLiveData<Boolean>()
    val startDialogDownload
        get() = _startDialogDownload


    init {
        checkDataExist()
        initializeFetch()
    }

    private fun initializeFetch() {
        repository.getFilterList()?.let {
            _filterList = it
        }

    }

    private fun checkDataExist() {
        if (!file.exists()) {
            startDialogDownload.value = true
        }else downloadConfigSetupAndStart()
    }

    private fun downloadConfigSetupAndStart() {
        val config = PRDownloaderConfig.newBuilder().setDatabaseEnabled(true).build()
        PRDownloader.initialize(context, config)
        startDownload()
    }

    fun onFilterClicked(id: Long) {
        _navigateFilteredOwners.value = id
    }

    private fun startDownload(): Int {

        if (!file.exists()) file.mkdir()

        return PRDownloader.download(
            Utility.DOWNLOAD_URL,
            file.absolutePath,
            CAR_OWNER_DATA
        )
            .build()
            .setOnStartOrResumeListener {
                Timber.i("Started")
            }
            .setOnPauseListener {
                Timber.i("Paused")
            }
            .setOnCancelListener {
                Timber.i("Cancelled")
            }
            .setOnProgressListener { }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Timber.i("Successful")
                }

                override fun onError(error: com.downloader.Error?) {
                    Timber.e(error?.serverErrorMessage)
                }
            })
    }


    override fun onCleared() {
        super.onCleared()

    }
}
