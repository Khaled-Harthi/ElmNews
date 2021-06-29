package com.example.elmnewsapp.data.api

import com.example.elmnewsapp.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NewsClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.NEWS_BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}