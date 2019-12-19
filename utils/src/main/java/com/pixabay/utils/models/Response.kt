package com.pixabay.utils.models

sealed class Response
data class Success<out T>(val data: T?) : Response()
data class Loading(val msg: String?) : Response()
data class ErrorIn(val code: Int?=null, val message: String?) : Response()