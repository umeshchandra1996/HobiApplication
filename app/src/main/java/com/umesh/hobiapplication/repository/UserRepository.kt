package com.umesh.hobiapplication.repository

import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.network.data.NetworkResponse

interface UserRepository {

    fun addUser(users: Users):Long

    fun addUserList(users: List<Users>):List<Long>

    fun deleteUser(users: Users)

    fun verifyLoginUser(mobNum:String,password:String): Users

    fun getUserDataDetails(id:Long):Users

    suspend fun getDataFromNetwork(): NetworkResponse

}