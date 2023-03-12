package com.nssaakyan.searchfilm

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.transition.Scene
import android.transition.Transition
import android.transition.Slide
import android.transition.TransitionSet
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merge_home_screen_content.*


class HomeFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var isEnter = true
    // Список фильмов
    private val filmsDataBase = listOf(
        Film(R.string.avatar_title, R.drawable.avatar, R.string.avatar_desc),
        Film(R.string.puss_title, R.drawable.puss_in_boots, R.string.puss_desc),
        Film(R.string.elvis_title, R.drawable.elvis, R.string.elvis_desc),
        Film(R.string.batman_title, R.drawable.batman, R.string.batman_desc),
        Film(R.string.boite_title, R.drawable.boite_noire, R.string.boite_desc),
        Film(R.string.train_title, R.drawable.bullet_train, R.string.train_desc),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("RtlHardcoded")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scene = Scene.getSceneForLayout(home_fragment_root, R.layout.merge_home_screen_content, requireContext())
        val searchSlide = Slide(Gravity.RIGHT).addTarget(R.id.search_view1)
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        val customTransition = TransitionSet().apply {
            duration = 500
            addTransition(recyclerSlide)
            addTransition(searchSlide)
        }
        if (isEnter) {
            TransitionManager.go(scene, customTransition)
            isEnter = false
        } else {
            TransitionManager.go(scene)
        }

        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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


        main_recycler.apply {
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
