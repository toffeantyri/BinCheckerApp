package ru.testwork.bincheckerapp.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val alpha2: String?,
    val currency: String?,
    val emoji: String?,
    val latitude: Int?,
    val longitude: Int?,
    val name: String?,
    val numeric: String?
)