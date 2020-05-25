package com.pierreherisse.poketlin.network

import retrofit2.Response
import retrofit2.http.*

interface PokemonWebService {
    @GET("pokemon")
    suspend fun getPokemons(): Response<ApiResponse>
}