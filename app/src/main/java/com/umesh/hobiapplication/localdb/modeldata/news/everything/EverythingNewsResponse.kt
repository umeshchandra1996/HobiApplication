package com.umesh.hobiapplication.localdb.modeldata.news.everything

data class EverythingNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)