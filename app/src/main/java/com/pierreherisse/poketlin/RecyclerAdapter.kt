package com.pierreherisse.poketlin

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val pokemons: ArrayList<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = pokemons[position]
        holder.bindPhoto(itemPhoto)
    }

    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var pokemon: Pokemon? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, PokemonActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, pokemon)
            context.startActivity(showPhotoIntent)
        }

        fun bindPhoto(pokemon: Pokemon) {
            this.pokemon = pokemon
            Picasso.with(view.context).load(pokemon.url).into(view.itemImage)
            view.itemDate.text = pokemon.humanDate
            view.itemDescription.text = pokemon.explanation
        }

        companion object {
            private const val PHOTO_KEY = "PHOTO"
        }
    }
}