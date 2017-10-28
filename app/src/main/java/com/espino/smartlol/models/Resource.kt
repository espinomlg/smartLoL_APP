package com.espino.smartlol.models

/**
 * Created by espino on 28/10/17.
 */
data class Resource<T>(val status: Int, val statusCode: Int, val data: T?, val message: String?){

    companion object {
        val SUCCESS = 1
        val ERROR = 0

        fun<T> success(statusCode: Int, data: T): Resource<T> = Resource(SUCCESS, statusCode, data, null)

        fun error(statusCode: Int, message: String?) = Resource(ERROR, statusCode, null, message)
    }
}