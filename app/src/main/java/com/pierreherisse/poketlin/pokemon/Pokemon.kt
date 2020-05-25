package com.pierreherisse.poketlin.pokemon

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Pokemon (
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "id")
    val id: String
): Serializable, Parcelable