package com.pixabay.utils

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pixabay.repo.db.ImagesDAO
import com.pixabay.utils.entities.ImageModel
import dagger.Binds


@Database(entities = [ImageModel::class], version = 1)
 abstract class DBHelper : RoomDatabase() {



    abstract fun getImagesDAO(): ImagesDAO

//      fun imagesDao(): ImagesDAO {
//          synchronized(DBHelper::class) {
//              return Room.databaseBuilder(
//                  context,
//                  DBHelper::class.java, "pixabay.db"
//              )
//                  .allowMainThreadQueries()
//                  .fallbackToDestructiveMigration()
//                  .build().imagesDao()
//          }
//      }
//
//    fun getInstance(): DBHelper? {
//        synchronized(DBHelper::class) {
//            return Room.databaseBuilder(
//                context,
//                DBHelper::class.java, "pixabay.db"
//            )
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//    }

}