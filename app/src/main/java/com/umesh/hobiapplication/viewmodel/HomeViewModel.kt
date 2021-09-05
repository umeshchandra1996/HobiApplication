package com.umesh.hobiapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.resource.Resource
import com.umesh.hobiapplication.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val usersUseCase: UsersUseCase) :ViewModel(){
    private val _GetUserDataStatus = MutableLiveData<Resource<Users>>()

    val getUserDataStatus: MutableLiveData<Resource<Users>> = _GetUserDataStatus

    fun GetUserLoginDataStatus(id:Long) {
        viewModelScope.launch {
            _GetUserDataStatus.postValue(Resource.loading(null))
            try {
                val data = usersUseCase.getUserData(id)
                _GetUserDataStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _GetUserDataStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }
}