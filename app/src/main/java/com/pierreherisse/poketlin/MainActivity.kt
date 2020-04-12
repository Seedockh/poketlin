package com.pierreherisse.poketlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), PokemonRequester.PokemonRequesterResponse {
    private var pokemonsList: ArrayList<Pokemon> = ArrayList()
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var pokemonRequester: PokemonRequester
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private val lastVisibleItemPosition: Int
        get() = if (recyclerView.layoutManager == linearLayoutManager) linearLayoutManager.findLastVisibleItemPosition()
                else gridLayoutManager.findLastVisibleItemPosition()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(pokemonsList)
        recyclerView.adapter = adapter
        setRecyclerViewScrollListener()

        pokemonRequester = PokemonRequester(this)
        gridLayoutManager = GridLayoutManager(this, 2)
    }

    override fun onStart() {
        super.onStart()

        if (pokemonsList.size == 0) {
            requestPokemon()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_recycler_manager) {
            changeLayoutManager()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestPokemon() {
        try {
            pokemonRequester.getPokemon()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun receivedNewPokemon(newPokemon: Pokemon) {
        runOnUiThread {
            pokemonsList.add(newPokemon)
            adapter.notifyItemInserted(pokemonsList.size-1)
        }
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!pokemonRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestPokemon()
                }
            }
        })
    }

    private fun changeLayoutManager() {
        if (recyclerView.layoutManager == linearLayoutManager) {
            recyclerView.layoutManager = gridLayoutManager
            if (pokemonsList.size == 1) requestPokemon()
        } else recyclerView.layoutManager = linearLayoutManager
    }
}
