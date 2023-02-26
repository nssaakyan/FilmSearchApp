package com.nssaakyan.searchfilm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: Int,
    val poster: Int,
    val description: Int
): Parcelable