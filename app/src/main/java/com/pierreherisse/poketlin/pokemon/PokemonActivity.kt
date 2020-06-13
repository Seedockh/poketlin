package com.pierreherisse.poketlin.pokemon

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pierreherisse.poketlin.R

class PokemonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokemonId = intent.getSerializableExtra("id") as String
        val pokemonDetails =
        Log.d("id sent : ", "$pokemonId")
    }
}