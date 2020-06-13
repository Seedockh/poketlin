package com.pierreherisse.poketlin.pokemon

import android.util.Log
import com.pierreherisse.poketlin.network.Api

class PokemonRepository  {
    private val pokemonWebService = Api.pokemonWebService

    suspend fun getAll(): List<Pokemon>? {
        val pokemonsResponse = pokemonWebService.getPokemons()
        if (pokemonsResponse.isSuccessful) {
            return pokemonsResponse.body()?.results
        }
        return null
    }

    suspend fun getDetails(id: String) {
        val detailsResponse = pokemonWebService.getPokemon(id)

        Log.d("API RESPONSE", "$detailsResponse")
    }
}