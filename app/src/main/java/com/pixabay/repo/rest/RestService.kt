package com.pixabay.repo.rest

import com.pixabay.domain.entities.ResponseModel
import retrofit2.http.*

interface RestService {

    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String
    ): ResponseModel

}