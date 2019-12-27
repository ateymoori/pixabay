package com.pixabay.utils.models

sealed class Response
data class Success(val data: Any?) : Response()
data class Loading(val msg: String?) : Response()
data class ErrorIn(val code: Int?=null, val message: String?) : Response()