package com.darklabs.businessdomain.network.util

import com.darklabs.businessdomain.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", BuildConfig.NEWS_API_KEY)
                .build()
        )
    }

}