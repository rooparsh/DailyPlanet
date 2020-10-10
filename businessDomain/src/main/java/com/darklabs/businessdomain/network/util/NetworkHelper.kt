package com.darklabs.businessdomain.network.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Response<T> {
    return withContext(exceptionHandler) {
        apiCall.invoke().resolveResponse()
    }
}

private fun <T> Response<T>.resolveResponse(): Response<T> {
    return if (this.isSuccessful) {
        this
    } else {
        Response.error(this.code(), this.errorBody()!!)
    }
}

private val exceptionHandler =
    CoroutineExceptionHandler { _, exception ->
        Response.error<Exception>(
            400,
            exception.localizedMessage?.toResponseBody() ?: run { "".toResponseBody() }
        )
    }