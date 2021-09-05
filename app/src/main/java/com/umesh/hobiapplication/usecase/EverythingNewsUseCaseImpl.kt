package com.umesh.hobiapplication.usecase

import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import com.umesh.hobiapplication.repository.EverythingNewsRepository
import retrofit2.Response
import javax.inject.Inject

class EverythingNewsUseCaseImpl @Inject constructor(private var repository: EverythingNewsRepository):EverythingNewsUseCase {
    override suspend fun getEverythingNewsByDefault(keyword:String): EverythingNewsResponse {
        return repository.getEverythingNews(keyword)
    }
}