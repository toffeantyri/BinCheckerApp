package ru.testwork.bincheckerapp.data.models.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BinInfoModel(
    var binCode: Int = 0,
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BinInfoModel) return false
        if (this.binCode != other.binCode) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}