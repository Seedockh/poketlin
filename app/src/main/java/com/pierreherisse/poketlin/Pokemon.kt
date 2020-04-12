package com.pierreherisse.poketlin

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Pokemon(pokemonJSON: JSONObject) : Serializable {

  private lateinit var pokemonDate: String
  lateinit var humanDate: String
    private set
  lateinit var explanation: String
    private set
  lateinit var url: String
    private set

  init {
    try {
      pokemonDate = pokemonJSON.getString(POKEMON_DATE)
      humanDate = convertDateToHumanDate()
      explanation = pokemonJSON.getString(POKEMON_EXPLANATION)
      url = pokemonJSON.getString(POKEMON_URL)
    } catch (e: JSONException) {
      e.printStackTrace()
    }

  }

  private fun convertDateToHumanDate(): String {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val humanDateFormat = SimpleDateFormat("dd MMMM yyyy")
    try {
      val parsedDateFormat = dateFormat.parse(pokemonDate)
      val cal = Calendar.getInstance()
      cal.time = parsedDateFormat
      return humanDateFormat.format(cal.time)
    } catch (e: ParseException) {
      e.printStackTrace()
      return ""
    }

  }

  companion object {
    private val POKEMON_DATE = "date"
    private val POKEMON_EXPLANATION = "explanation"
    private val POKEMON_URL = "url"
  }
}
