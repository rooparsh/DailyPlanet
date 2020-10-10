package com.darklabs.businessdomain.network.api

import com.darklabs.businessdomain.network.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(GET_TOP_HEADLINES)
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int
    ): Response<NewsResponse>
}