package com.umesh.hobiapplication.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.network.data.NetworkResponse
import com.umesh.hobiapplication.resource.Resource
import com.umesh.hobiapplication.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var usersUseCase: UsersUseCase):ViewModel() {
    private val _GetUserLoginDataStatus = MutableLiveData<Resource<Users>>()

    val getUserLoginDataStatus: MutableLiveData<Resource<Users>> = _GetUserLoginDataStatus

  private val _GetUserProfileDataStatus = MutableLiveData<Resource<Users>>()

    val getUserProfileDataStatus: MutableLiveData<Resource<Users>> = _GetUserProfileDataStatus


    private val _getNetworkDataStatus = MutableLiveData<Resource<NetworkResponse>>()

    val getNetworkDataStatus: MutableLiveData<Resource<NetworkResponse>> = _getNetworkDataStatus

    fun getUserLoginDataStatus(mobNum:String,password:String) {
        viewModelScope.launch {
            _GetUserLoginDataStatus.postValue(Resource.loading(null))
            try {
                val data = usersUseCase.getUserLoginVerify(mobNum,password)
                _GetUserLoginDataStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _GetUserLoginDataStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

    fun getUserProfileData(id:Long){
        viewModelScope.launch {
            _GetUserProfileDataStatus.postValue(Resource.loading(null))
            try {
                val data = usersUseCase.getUserData(id)
                _GetUserProfileDataStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _GetUserProfileDataStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

    fun getDataFormNetwork(){
        viewModelScope.launch {
            _getNetworkDataStatus.postValue(Resource.loading(null))
            try {
                val data = usersUseCase.getDataFromNetworkUseCase()
                _getNetworkDataStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _getNetworkDataStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

}