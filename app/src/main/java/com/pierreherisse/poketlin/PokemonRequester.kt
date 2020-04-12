package com.pierreherisse.poketlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.net.Uri.Builder
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PokemonRequester(listeningActivity: AppCompatActivity) {

  interface PokemonRequesterResponse {
    fun receivedNewPokemon(newPokemon: Pokemon)
  }

  private val calendar: Calendar = Calendar.getInstance()
  private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
  private val responseListener: PokemonRequesterResponse
  private val context: Context
  private val client: OkHttpClient
  var isLoadingData: Boolean = false
    private set

  init {
    responseListener = listeningActivity as PokemonRequesterResponse
    context = listeningActivity.applicationContext
    client = OkHttpClient()
  }

  fun getPokemon() {
    val date = dateFormat.format(calendar.time)
    val urlRequest = Builder().scheme(URL_SCHEME)
        .authority(URL_AUTHORITY)
        .appendPath(URL_PATH_1)
        .appendPath(URL_PATH_2)
        .appendQueryParameter(URL_QUERY_PARAM_DATE_KEY, date)
        .appendQueryParameter(URL_QUERY_PARAM_API_KEY, context.getString(R.string.api_key))
        .build().toString()

    val request = Request.Builder().url(urlRequest).build()
    isLoadingData = true

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        isLoadingData = false
        e.printStackTrace()
      }

      override fun onResponse(call: Call, response: Response) {

        try {
          val pokemonJSON = JSONObject(response.body()!!.string())

          calendar.add(Calendar.DAY_OF_YEAR, -1)

          if (pokemonJSON.getString(MEDIA_TYPE_KEY) != MEDIA_TYPE_VIDEO_VALUE) {
            val receivedPokemon = Pokemon(pokemonJSON)
            responseListener.receivedNewPokemon(receivedPokemon)
            isLoadingData = false
          } else {
            getPokemon()
          }
        } catch (e: JSONException) {
          isLoadingData = false
          e.printStackTrace()
        }
      }
    })
  }

  companion object {
    private const val MEDIA_TYPE_KEY = "media_type"
    private const val MEDIA_TYPE_VIDEO_VALUE = "video"
    private const val URL_SCHEME = "https"
    private const val URL_AUTHORITY = "api.nasa.gov"
    private const val URL_PATH_1 = "planetary"
    private const val URL_PATH_2 = "apod"
    private const val URL_QUERY_PARAM_DATE_KEY = "date"
    private const val URL_QUERY_PARAM_API_KEY = "api_key"
  }
}
