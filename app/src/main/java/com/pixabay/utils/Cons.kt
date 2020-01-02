package com.pixabay.utils


class Cons {
    companion object {
        const val BASE_URL = "https://pixabay.com/api/"
        const val API_KEY = "14649220-5ae78e4612f86b869152790a4"
        const val MIN_SEARCH_WORD_COUNT: Long = 2
        const val DB_VERSION: Int = 1

        //in milli seconds
        const val SEARCH_DO_DELAY: Long = 600

        //"all", "photo", "illustration", "vector"
        const val IMAGES_TYPE = "photo"
        val DEFAULT_SEARCH_WORD = listOf("fruits").random()
        const val DB_NAME = "pixabay.db"

        const val ITEM_BUNDLE = "item"
        const val TAGS_DELIMITER = ","
    }
}