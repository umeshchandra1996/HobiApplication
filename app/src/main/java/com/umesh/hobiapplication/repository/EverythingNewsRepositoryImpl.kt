package com.umesh.hobiapplication.repository

import com.umesh.hobiapplication.localdb.dao.UsersDao
import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import com.umesh.hobiapplication.network.ApiService
import com.umesh.hobiapplication.network.Constants
import com.umesh.hobiapplication.network.data.NetworkResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EverythingNewsRepositoryImpl @Inject constructor(
    private var usersDao: UsersDao,
    private var apiService: ApiService
) : EverythingNewsRepository {
    
    override suspend fun getEverythingNews(keyword:String): EverythingNewsResponse {
       return apiService.getEverythingNews(keyword,1,Constants.API_KEY)
    }
}