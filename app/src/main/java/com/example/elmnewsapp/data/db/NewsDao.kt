package com.example.elmnewsapp.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elmnewsapp.data.model.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(news : List<News>)

    @Query("SELECT * FROM news")
    fun pagingSource() : PagingSource<Int, News>


    @Query("SELECT COUNT(*) FROM news")
    fun count() : LiveData<Int>
}