package com.umesh.hobiapplication.usecase

import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.network.data.NetworkResponse

interface UsersUseCase {
    suspend fun addUser(users: Users): Long

    suspend fun addUserList(users: List<Users>): List<Long>
    suspend fun getUserLoginVerify(mobNum: String, password: String): Users
    suspend fun getUserData(id:Long): Users

    suspend fun getDataFromNetworkUseCase(): NetworkResponse

}