package com.idris.aminu.android.viewModel.owner


import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.lifecycle.*
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.idris.aminu.android.models.CarOwnerList
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.repository.FilterRepository
import com.idris.aminu.android.util.Utility
import com.idris.aminu.android.util.Utility.CAR_OWNER_DATA
import com.idris.aminu.android.view.carOwnerList.FilterManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File


class CarOwnerViewModel(private val data: FilterElement) : ViewModel() {

    private val absoluteFile by lazy {
        File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            Utility.FOLDER.plus("/$CAR_OWNER_DATA")
        )
    }
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _filterResult = MutableLiveData<CarOwnerList>()
    val filterResult: LiveData<CarOwnerList>
            get() = _filterResult



    init {
        val filterManager = FilterManager(absoluteFile)
        uiScope.launch {
           _filterResult.value = filterManager.filter(data)
        }


    }


}
