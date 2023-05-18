package com.nssaakyan.searchfilm.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.bumptech.glide.Glide
import com.nssaakyan.searchfilm.R
import com.nssaakyan.searchfilm.data.ApiConstants
import com.nssaakyan.searchfilm.databinding.FragmentDetailsBinding
import com.nssaakyan.searchfilm.domain.Film

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    init {
        enterTransition = Slide(Gravity.END).apply { duration = 200 }
        returnTransition = Slide(Gravity.END).apply { duration = 200; mode = Slide.MODE_OUT }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilmsDetails()

        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )

        binding.detailsFabFavorites.setOnClickListener {
            if (!film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
                Toast.makeText(context, R.string.in_favorites, Toast.LENGTH_SHORT).show()
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
                Toast.makeText(context, R.string.out_favorites, Toast.LENGTH_SHORT).show()
            }
        }

        binding.detailsFabShare.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                R.string.share
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, R.string.share_with.toString()))
        }
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        film = arguments?.get("film") as Film

        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description
    }
}