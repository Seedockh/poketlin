package com.pierreherisse.poketlin.pokemon

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pierreherisse.poketlin.R

class PokemonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        //val name = findViewById<TextView>(R.id.pokemonName)
        //val image = findViewById<ImageView>(R.id.pokemonImage)
        //val pokemon: Pokemon? = intent?.getSerializableExtra("pokemon") as? Pokemon

        /*if(pokemon !== null){
            name.text = pokemon.name
        }*/
    }
}