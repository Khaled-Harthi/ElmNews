package com.example.elmnewsapp.data.repository

import androidx.paging.*
import com.example.elmnewsapp.Constants
import com.example.elmnewsapp.data.api.NewsAPI
import com.example.elmnewsapp.data.db.NewsDao
import com.example.elmnewsapp.data.db.NewsDatabase
import com.example.elmnewsapp.data.model.News
import com.example.elmnewsapp.data.paging.remoteMediator.NewsRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsAPI,
    private val newsDatabase: NewsDatabase,
    private val newsDao: NewsDao
) {

    @ExperimentalPagingApi
    fun getNews() : Flow<PagingData<News>> = Pager(
        config = PagingConfig(pageSize = Constants.NEWS_PAGE_SIZE, prefetchDistance = 2),
        remoteMediator = NewsRemoteMediator(newsService, newsDatabase, newsDao)
    ) {
        newsDao.pagingSource()
    }.flow


}