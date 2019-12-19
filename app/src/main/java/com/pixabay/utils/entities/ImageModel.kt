package com.pixabay.utils.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "images")
data class ImageModel(
    val comments: Int?,
    val downloads: Int?,
    val favorites: Int?,
    val fullHDURL: String?,
    @PrimaryKey
    val id: Int,
    val imageHeight: Int?,
    val imageSize: Int?,
    val imageURL: String?,
    val imageWidth: Int?,
    val largeImageURL: String?,
    val likes: Int?,
    val pageURL: String?,
    val previewHeight: Int?,
    val previewURL: String?,
    val previewWidth: Int?,
    val tags: String?,
    val type: String?,
    val user: String?,
    val userImageURL: String?,
    val user_id: Int?,
    val views: Int?,
    val webformatHeight: Int,
    val webformatURL: String,
    var searchWord: String?,
    val webformatWidth: Int?
) : Parcelable