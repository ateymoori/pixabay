package com.pixabay.utils.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel(
    val hits: List<ImageModel>,
    val total: Int,
    val totalHits: Int
): Parcelable