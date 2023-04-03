package com.nssaakyan.searchfilm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.film_item.view.*

class FilmViewHolder(private val
                     itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description


    fun bind(film: Film) {
        title.setText(film.title)
        Glide.with(itemView)
            .load(film.poster)
            .centerCrop()
            .into(poster)
        description.setText(film.description)
    }
}