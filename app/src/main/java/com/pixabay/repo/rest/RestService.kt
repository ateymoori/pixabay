package com.pixabay.repo.rest

import com.pixabay.utils.entities.ResponseModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RestService {

//    @GET("?")
//    suspend fun searchImage(
//        @Query("key") key: String,
//        @Query("q") q: String,
//        @Query("image_type") image_type: String
//    ): Single<Response<ResponseModel>>

    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String
    ): ResponseModel

}