package com.example.elmnewsapp.data.api

import com.example.elmnewsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v1/news?languages=en&categories=business")
    suspend fun getNews (
        @Query("access_key") accessKey : String,
        @Query("countries") countries : String,
        @Query("offset") offset : Int,
        @Query("limit") limit : Int
    ) : NewsResponse
}