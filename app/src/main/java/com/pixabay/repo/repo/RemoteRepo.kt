package com.pixabay.repo.repo

import com.pixabay.repo.rest.RestService
import com.pixabay.utils.Cons
import com.pixabay.utils.Cons.Companion.IMAGES_TYPE
import javax.inject.Inject


class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.searchImage(
            key = Cons.API_KEY,
            image_type = IMAGES_TYPE,
            q = word
        )
}