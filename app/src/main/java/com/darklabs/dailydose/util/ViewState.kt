package com.darklabs.dailydose.util

sealed class ViewState<ResultType> {
    data class Success<ResultType>(val data: ResultType) : ViewState<ResultType>()

    class Loading<ResultType> : ViewState<ResultType>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    data class Error<ResultType>(val message: String?, val throwable: Throwable?) :
        ViewState<ResultType>()

    companion object {
        fun <ResultType> success(data: ResultType): ViewState<ResultType> = Success(data)
        fun <ResultType> loading(): ViewState<ResultType> = Loading()
        fun <ResultType> error(
            message: String? = null,
            throwable: Throwable? = null
        ): ViewState<ResultType> =
            Error(message, throwable)
    }
}