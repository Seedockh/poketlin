package com.pierreherisse.poketlin.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierreherisse.poketlin.R
import com.pierreherisse.poketlin.pokemon.Pokemon
import kotlinx.android.synthetic.main.fragment_pokedex.*

class PokedexFragment : Fragment() {

    private var adapter = PokedexAdapter()
    private val viewModelPokemon by lazy {
        ViewModelProvider(this).get(PokedexViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModelPokemon.pokedex.observe(this, Observer { newList ->
            adapter.pokedex = newList.orEmpty()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModelPokemon.loadPokemons()
    }
}