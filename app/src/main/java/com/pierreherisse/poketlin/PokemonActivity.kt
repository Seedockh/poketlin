package com.pierreherisse.poketlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

  private var selectedPokemon: Pokemon? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_pokemon)

    selectedPokemon = intent.getSerializableExtra(POKEMON_KEY) as Pokemon
    Picasso.with(this).load(selectedPokemon?.url).into(pokemonImageView)

    pokemonDescription?.text = selectedPokemon?.explanation
  }

  companion object {
    private const val POKEMON_KEY = "PHOTO"
  }
}
