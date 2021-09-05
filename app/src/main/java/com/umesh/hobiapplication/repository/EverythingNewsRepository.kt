package com.umesh.hobiapplication.repository

import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import retrofit2.Response

interface EverythingNewsRepository {
    suspend fun getEverythingNews(keyword:String): EverythingNewsResponse
}