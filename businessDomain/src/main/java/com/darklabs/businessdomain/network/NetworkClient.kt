package com.darklabs.businessdomain.network

import com.darklabs.businessdomain.BuildConfig
import com.darklabs.businessdomain.network.api.NewsApi
import com.darklabs.businessdomain.network.util.HeaderInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkClient {

    private val contentType = "application/json".toMediaType()

    private fun createOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            httpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        httpBuilder.addInterceptor(HeaderInterceptor())
        return httpBuilder.build()
    }

    private fun createServiceProvider(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(createJsonConfig().asConverterFactory(contentType))
        .build()

    private fun createJsonConfig() = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun provideNetworkDataProvider(): NewsApi {
        val retrofit = createServiceProvider(createOkHttpClient())
        return retrofit.create(NewsApi::class.java)
    }

}