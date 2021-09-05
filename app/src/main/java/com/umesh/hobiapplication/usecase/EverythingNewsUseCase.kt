package com.umesh.hobiapplication.usecase

import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import retrofit2.Response


interface EverythingNewsUseCase {
 suspend fun getEverythingNewsByDefault(keyword:String): EverythingNewsResponse
}