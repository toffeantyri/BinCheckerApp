package ru.testwork.bincheckerapp.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    val city: String?,
    val name: String?,
    val phone: String?,
    val url: String?
)