package com.nssaakyan.searchfilm.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nssaakyan.searchfilm.data.ApiConstants
import com.nssaakyan.searchfilm.domain.Film
import kotlinx.android.synthetic.main.film_item.view.*

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description
    private val ratingDonut = itemView.rating_donut


    fun bind(film: Film) {
        title.text = film.title
        Glide.with(itemView)
            .load(ApiConstants.IMAGES_URL + "w342" + film.poster)
            .centerCrop()
            .into(poster)
        description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}