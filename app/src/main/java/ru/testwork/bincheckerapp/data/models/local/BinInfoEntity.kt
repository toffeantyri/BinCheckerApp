package ru.testwork.bincheckerapp.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.testwork.bincheckerapp.data.models.remote.Bank
import ru.testwork.bincheckerapp.data.room.TABLE_NAME_BININFO

@Entity(tableName = TABLE_NAME_BININFO)
data class BinInfoEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "bin_code") var binCode: Int = 0,
    //Embedded example
    @Embedded val bank: Bank?,
    @ColumnInfo(name = "brand") val brand: String? = null,
    //Country
    @ColumnInfo(name = "alpha2") val alpha2C: String? = null,
    @ColumnInfo(name = "currency") val currencyC: String? = null,
    @ColumnInfo(name = "emoji") val emojiC: String? = null,
    @ColumnInfo(name = "latitude") val latitudeC: Int? = null,
    @ColumnInfo(name = "longitude") val longitudeC: Int? = null,
    @ColumnInfo(name = "name_c") val nameC: String? = null,
    @ColumnInfo(name = "numeric") val numericC: String? = null,
    //Number
    @ColumnInfo(name = "number_length") val numberLength: Int? = null,
    @ColumnInfo(name = "number_lehn") val numberLuhn: Boolean? = null,

    @ColumnInfo(name = "prepaid") val prepaid: Boolean? = null,
    @ColumnInfo(name = "scheme") val scheme: String? = null,
    @ColumnInfo(name = "type") val type: String? = null
)