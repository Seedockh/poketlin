package com.pierreherisse.poketlin.pokemon

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
}