package com.idris.aminu.android.viewModel.owner


import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idris.aminu.android.models.CarOwnerList
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.util.Utility
import com.idris.aminu.android.util.Utility.CAR_OWNER_DATA
import com.idris.aminu.android.view.carOwnerList.FilterManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    private var _isDbAvailable = MutableLiveData<Boolean>()
    val isDbAvailable: LiveData<Boolean>
        get() = _isDbAvailable


    init {
        if (!absoluteFile.exists()) {
            _isDbAvailable.value = false

        } else {
            val filterManager = FilterManager()
            uiScope.launch {
                val fileList = filterManager.readFile(absoluteFile)
                _filterResult.value = filterManager.filter(fileList, data)
            }
            _isDbAvailable.value = true
        }


    }


}
