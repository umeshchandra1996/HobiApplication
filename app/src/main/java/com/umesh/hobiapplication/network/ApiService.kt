package com.umesh.hobiapplication.network

import com.umesh.hobiapplication.localdb.modeldata.news.everything.EverythingNewsResponse
import com.umesh.hobiapplication.network.data.NetworkResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("albums/")
    suspend fun getAlbums(): NetworkResponse

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Long): Single<List<String>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") id: Long): Single<String>

    @GET("everything")
    suspend fun getEverythingNews(
        @Query("q") keyword: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String
    ): EverythingNewsResponse

}