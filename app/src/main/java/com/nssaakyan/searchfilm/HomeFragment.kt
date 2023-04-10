package com.nssaakyan.searchfilm

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.annotation.RequiresApi
import com.nssaakyan.searchfilm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    // Список фильмов
    private val filmsDataBase = listOf(
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RtlHardcoded")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase as MutableList<Film>)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    requireContext().resources.getString(it.title).lowercase().startsWith(newText.lowercase())
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result as MutableList<Film>)
                return true
            }
        })

        binding.mainRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    (requireActivity() as MainActivity).launchDetailsFragment(film)
                }
            })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    //Кладем нашу БД в RV
    filmsAdapter.addItems(filmsDataBase as MutableList<Film>)
    }
}
