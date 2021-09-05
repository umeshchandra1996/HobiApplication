package com.umesh.hobiapplication.repository

import com.umesh.hobiapplication.localdb.dao.UsersDao
import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.network.ApiService
import com.umesh.hobiapplication.network.Constants
import com.umesh.hobiapplication.network.data.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private  var usersDao: UsersDao,
    private var apiService: ApiService

):UserRepository{

    override fun addUser(users:Users): Long {
       return usersDao.insertUser(users)
    }

    override fun addUserList(users:List<Users>): List<Long> {
       return usersDao.insertUserAll(users)
    }

    override fun deleteUser(users: Users) {
        TODO("Not yet implemented")
    }

    override fun verifyLoginUser(mobNum:String,password:String): Users {
        return usersDao.readLoginData(mobNum = mobNum,password = password )
    }

    override fun getUserDataDetails(id:Long): Users {
        return usersDao.getUserDataDetails(id)
    }

    override suspend fun getDataFromNetwork(): NetworkResponse {
        return apiService.getAlbums(/*"keyword",1,Constants.API_KEY*/)
    }


}