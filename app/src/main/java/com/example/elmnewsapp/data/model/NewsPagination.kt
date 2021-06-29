package com.example.elmnewsapp.data.model

import com.squareup.moshi.Json

data class NewsPagination (
    @Json(name = "limit") val limit : Int,
    @Json(name = "offset") val offset : Int,
    @Json(name = "count") val count : Int,
    @Json(name = "total") val total : Int
        )
