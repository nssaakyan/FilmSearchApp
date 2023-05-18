package com.nssaakyan.searchfilm.view.fragments;

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nssaakyan.searchfilm.view.rv_adapters.FilmListRecyclerAdapter
import com.nssaakyan.searchfilm.view.MainActivity
import com.nssaakyan.searchfilm.R
import com.nssaakyan.searchfilm.view.rv_adapters.TopSpacingItemDecoration
import com.nssaakyan.searchfilm.databinding.FragmentFavoritesBinding
import com.nssaakyan.searchfilm.domain.Film

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val favoritesList = listOf(
        Film(R.string.avatar_title, R.drawable.avatar, R.string.avatar_desc, 7.3f),
        Film(R.string.puss_title, R.drawable.puss_in_boots, R.string.puss_desc,5.5f),
        Film(R.string.elvis_title, R.drawable.elvis, R.string.elvis_desc, 8.2f),
        Film(R.string.batman_title, R.drawable.batman, R.string.batman_desc, 5.6f),
        Film(R.string.boite_title, R.drawable.boite_noire, R.string.boite_desc, 7.5f),
        Film(R.string.train_title, R.drawable.bullet_train, R.string.train_desc, 6.9f),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(favoritesList as MutableList<Film>)
    }
}