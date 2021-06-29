package com.example.elmnewsapp.data.di

import android.content.Context
import com.example.elmnewsapp.data.db.NewsDao
import com.example.elmnewsapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsDBModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext appContext : Context) : NewsDatabase {
        return NewsDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideNewsDao (newsDatabase: NewsDatabase) : NewsDao {
        return newsDatabase.newsDao()
    }
}