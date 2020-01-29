package com.pixabay.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pixabay.repo.db.ImagesDAO
import com.pixabay.utils.Cons.Companion.DB_VERSION
import com.pixabay.domain.entities.ImageModel


@Database(entities = [ImageModel::class], version = DB_VERSION)
abstract class DBHelper : RoomDatabase() {
    abstract fun getImagesDAO(): ImagesDAO
}