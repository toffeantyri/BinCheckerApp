package ru.testwork.bincheckerapp.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    val length: Int?,
    val luhn: Boolean?
)