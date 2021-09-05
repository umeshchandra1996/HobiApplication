package com.umesh.hobiapplication.viewmodel.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import com.umesh.hobiapplication.resource.Resource
import com.umesh.hobiapplication.usecase.EverythingNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(var useCase: EverythingNewsUseCase):ViewModel(){
    private val _getEveryThingNewsDataStatus = MutableStateFlow<Resource<EverythingNewsResponse>>(Resource.loading(null))

    val getEveryThingNewsDataStatus: StateFlow<Resource<EverythingNewsResponse>> = _getEveryThingNewsDataStatus

    fun getEveryThingNews(keyword:String) {
        viewModelScope.launch {
            _getEveryThingNewsDataStatus.value=(Resource.loading(null))
            try {
                val data = useCase.getEverythingNewsByDefault(keyword)
                _getEveryThingNewsDataStatus.value=(Resource.success(data, 0))
            } catch (exception: Exception) {
                _getEveryThingNewsDataStatus.value=(Resource.error(null, exception.message!!))
            }
        }
    }

}