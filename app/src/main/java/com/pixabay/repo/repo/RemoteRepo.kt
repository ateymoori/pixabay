package com.pixabay.repo.repo

import com.pixabay.repo.rest.RestService
import com.pixabay.utils.Cons
import javax.inject.Inject


class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.searchImage(
            key = Cons.API_KEY,
            image_type = Cons.IMAGES_TYPE,
            q = word
        )
}