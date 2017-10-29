package com.espino.smartlol.webservice

import okhttp3.Headers


data class NetworkErrorResponse(val statusCode: Int, val headers: Headers? = null, val message: String?= null)