package com.pixabay.repo.repo

import com.pixabay.repo.db.ImagesDAO
import com.pixabay.domain.entities.ImageModel
import javax.inject.Inject

class DBRepo @Inject
constructor(private val imageDAO: ImagesDAO) {

    fun insertAll(images: List<ImageModel>?) {
        if (images != null)
            imageDAO.insertAll(images)
    }
    fun search(word: String) = imageDAO.getImagesBySearchWord(word)
}