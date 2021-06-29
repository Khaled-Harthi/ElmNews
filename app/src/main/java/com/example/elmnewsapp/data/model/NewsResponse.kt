package com.example.elmnewsapp.data.model

import com.squareup.moshi.Json

data class NewsResponse (
    @Json(name = "pagination") val pagination : NewsPagination,
    @Json(name = "data") val data : List<News>
)
