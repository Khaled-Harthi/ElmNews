package com.example.elmnewsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "news")
data class News (
    @PrimaryKey (autoGenerate = true) val id : Int,
    @ColumnInfo(name = "author", defaultValue = "-") @Json(name = "author") val author : String?,
    @Json(name = "title") val title : String?,
    @Json(name = "description") val description : String?,
    @Json(name = "url") val url : String?,
    @Json(name = "source") val source : String?,
    @Json(name = "image") val image : String?,
    @Json(name = "category") val category : String?,
    @Json(name = "language") val language : String?,
    @Json(name = "country") val country : String?,
    @Json(name = "published_at") val published_at : String?
    )
