package com.pierreherisse.poketlin.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierreherisse.poketlin.R
import com.pierreherisse.poketlin.pokemon.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder>() {
    var pokedex: List<Pokemon> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    var onItemClick: ((String) -> Unit)? = { }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            var pokemonUrl = pokemon.url.split("/")
            var pokemonId = pokemonUrl[pokemonUrl.lastIndex-1]
            var imageEndpoint = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png"


            Picasso.get().load(imageEndpoint).into(itemView.pokemonImage);

            itemView.pokemonName.text = pokemon.name.capitalize()
            itemView.setOnClickListener { onItemClick?.invoke("$pokemonId") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokedex.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokedex[position])
    }
}