package ru.testwork.bincheckerapp.data.models.remote

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    val city: String? = null,
    @field: Json(name = "name")
    @ColumnInfo(name = "bank_name")
    val bankName: String? = null,
    val phone: String? = null,
    val url: String? = null
)