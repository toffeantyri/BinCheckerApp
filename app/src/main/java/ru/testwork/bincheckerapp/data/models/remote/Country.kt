package ru.testwork.bincheckerapp.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val alpha2: String? = null,
    val currency: String? = null,
    val emoji: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
    val name: String? = null,
    val numeric: String? = null
)