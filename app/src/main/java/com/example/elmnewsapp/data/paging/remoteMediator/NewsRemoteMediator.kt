package com.example.elmnewsapp.data.paging.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.elmnewsapp.Constants
import com.example.elmnewsapp.data.api.NewsAPI
import com.example.elmnewsapp.data.db.NewsDao
import com.example.elmnewsapp.data.db.NewsDatabase
import com.example.elmnewsapp.data.model.News
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class NewsRemoteMediator
@Inject constructor(
    private val service : NewsAPI,
    private val db : NewsDatabase,
    private val dao : NewsDao)
    : RemoteMediator<Int, News>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, News>): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> db.withTransaction {
                    dao.count().value ?: 0
                }
            }

            val response = service.getNews(
                    accessKey = Constants.NEWS_API_KEY,
                    countries = Constants.NEWS_API_DEFAULT_COUNTRY,
                    offset = offset,
                    limit = Constants.NEWS_PAGE_SIZE
            )
            val page = response.pagination

            db.withTransaction {
                dao.saveAll(response.data)
            }


            MediatorResult.Success(endOfPaginationReached = (page.offset + page.count) >= page.total)
        } catch (e : Exception){
            MediatorResult.Error(e)
        }
    }

}