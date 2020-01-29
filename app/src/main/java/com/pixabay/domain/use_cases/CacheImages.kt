package com.pixabay.domain.use_cases

import com.pixabay.domain.entities.ImageModel

interface CacheImages {
    fun cacheAll(images:List<ImageModel>)
}