package com.sorcoz.domain.model

data class Resource<T>(
    val data: T?,
    val status: Status,
    val error: Throwable?
) {

    companion object {
        fun <T> error(e: Throwable, data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = data,
                error = e
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                status = Status.LOADING,
                data = data,
                error = null
            )
        }

        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = data,
                error = null
            )
        }
    }
}