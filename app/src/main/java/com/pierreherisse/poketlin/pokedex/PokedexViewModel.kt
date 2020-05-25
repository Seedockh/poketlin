package com.pierreherisse.poketlin.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierreherisse.poketlin.pokemon.Pokemon
import com.pierreherisse.poketlin.pokemon.PokemonRepository
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepository()
    private val _pokedex = MutableLiveData<List<Pokemon>>()
    val pokedex: LiveData<List<Pokemon>> = _pokedex

    fun loadPokemons() {
        viewModelScope.launch {
            val newList = pokemonRepository.getAll()
            _pokedex.value = newList
        }
    }
}