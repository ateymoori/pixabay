package com.pixabay.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixabay.utils.entities.ImageModel
import io.reactivex.Single


@Dao
interface ImagesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImageModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImageModel>)

    @Query("SELECT * FROM images WHERE searchWord = :word")
    fun getImagesBySearchWord(word: String): List<ImageModel>


    @Query("SELECT * FROM images ")
    fun getAll():  List<ImageModel>


    @Query("DELETE FROM images ")
    fun removeAll()


}