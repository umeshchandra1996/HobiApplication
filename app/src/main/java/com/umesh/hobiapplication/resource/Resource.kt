package com.umesh.hobiapplication.resource


data class Resource<out T>(val status: Status, val data: T?, val id: Long, val message: String?) {
    companion object {
        fun <T> success(data: T?, id: Long): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, id = id, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message, id = 0L)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null, id = 0L)
    }
}
