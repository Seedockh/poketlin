package com.pierreherisse.poketlin.network

import android.os.Parcelable
import com.pierreherisse.poketlin.pokemon.Pokemon
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ApiResponse (
    @field:Json(name="count")
    val count: Int,
    @field:Json(name = "next")
    val next: String?,
    @field:Json(name = "previous")
    val previous: String?,
    @field:Json(name = "results")
    val results: List<Pokemon>
): Serializable, Parcelable