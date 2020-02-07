package com.idris.aminu.android.viewModel.filter


import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.lifecycle.*
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.util.Utility
import com.idris.aminu.android.util.Utility.CAR_OWNER_DATA
import timber.log.Timber
import java.io.File


class FilterListViewModel(private val repository: FilterRepository) : ViewModel() {


    private val file by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER
        )
    }

    private val absoluteFile by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER.plus("/$CAR_OWNER_DATA")
        )
    }

    private var _filterList = MutableLiveData<Filter>()
    val filterList: LiveData<Filter>
        get() = _filterList


    private val _startDialogDownload = MutableLiveData<Boolean>()
    val startDialogDownload: LiveData<Boolean>
        get() = _startDialogDownload

    private val _completeDownload = MutableLiveData<Boolean>()
    val completeDownload: LiveData<Boolean>
        get() = _completeDownload

     val grantAccess = MutableLiveData<Boolean>()



    init {
        initializeFetch()
    }

    private fun initializeFetch() {
        Timber.i("Supposed called")
        repository.getFilterList()?.let {
            _filterList = it

        }

    }

    fun checkDataExist() {
        if (!absoluteFile.exists()) {
            _startDialogDownload.value = false
            startDownload()
        }


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
                    _completeDownload.value = true
                    grantAccess.value = true
                }

                override fun onError(error: com.downloader.Error?) {
                    Timber.e(error?.serverErrorMessage)
                    _completeDownload.value = true
                }
            })
    }


    override fun onCleared() {
        super.onCleared()

    }
}
