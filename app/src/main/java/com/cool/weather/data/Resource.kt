package com.cool.weather.data

// 包含其加载状态值的泛型类
@Suppress("unused")
data class Resource<T>(val status: Int, val data: T?, val message: String?) {
    companion object {
        private const val SUCCESS = 0
        private const val ERROR = 1
        private const val LOADING = 2
        fun <T> success(data: T?) = Resource(SUCCESS, data, null)
        fun <T> error(msg: String, data: T?) = Resource(ERROR, data, msg)
        fun <T> loading(data: T?) = Resource(LOADING, data, null)
    }
}
