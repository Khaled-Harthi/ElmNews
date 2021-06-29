package com.example.elmnewsapp.data.di

import com.example.elmnewsapp.data.api.NewsAPI
import com.example.elmnewsapp.data.api.NewsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NewsAPIModule {

    @Provides
    fun provideNewsAPI() : NewsAPI = NewsClient.buildService(NewsAPI::class.java)

}