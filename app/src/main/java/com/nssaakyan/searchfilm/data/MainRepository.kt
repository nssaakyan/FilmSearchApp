package com.nssaakyan.searchfilm.data

import com.nssaakyan.searchfilm.R
import com.nssaakyan.searchfilm.domain.Film

class MainRepository {
    val filmsDataBase = listOf(
        Film(R.string.avatar_title, R.drawable.avatar, R.string.avatar_desc, 7.3f),
        Film(R.string.puss_title, R.drawable.puss_in_boots, R.string.puss_desc, 5.5f),
        Film(R.string.elvis_title, R.drawable.elvis, R.string.elvis_desc, 8.2f),
        Film(R.string.batman_title, R.drawable.batman, R.string.batman_desc, 5.6f),
        Film(R.string.boite_title, R.drawable.boite_noire, R.string.boite_desc, 7.5f),
        Film(R.string.train_title, R.drawable.bullet_train, R.string.train_desc, 6.9f),
    )
}