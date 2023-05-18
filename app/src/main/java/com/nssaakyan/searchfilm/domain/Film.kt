package com.nssaakyan.searchfilm.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: Int,
    val poster: Int,
    val description: Int,
    var rating: Float = 0f,
    var isInFavorites: Boolean = false
): Parcelable