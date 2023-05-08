package com.nssaakyan.searchfilm.domain

import com.nssaakyan.searchfilm.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}