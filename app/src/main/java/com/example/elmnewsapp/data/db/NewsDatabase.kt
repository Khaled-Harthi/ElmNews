package com.example.elmnewsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elmnewsapp.data.model.News

@Database(
    entities = [News::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context)
                .also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NewsDatabase::class.java, "news.db")
            .build()
    }

}