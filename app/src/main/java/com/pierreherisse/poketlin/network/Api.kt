package com.pierreherisse.poketlin.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    private val moshi = Moshi.Builder().build()
    val pokemonWebService: PokemonWebService by lazy { retrofit.create(PokemonWebService::class.java) }
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}