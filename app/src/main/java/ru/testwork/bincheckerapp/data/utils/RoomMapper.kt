package ru.testwork.bincheckerapp.data.utils

import ru.testwork.bincheckerapp.data.models.local.BinInfoEntity
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.models.remote.Country
import ru.testwork.bincheckerapp.data.models.remote.Number

fun BinInfoEntity?.toBinInfoModel(): BinInfoModel? {
    return if (this == null) null
    else {
        BinInfoModel(
            binCode = this.binCode,
            bank = this.bank,
            brand = this.brand,
            country = Country(
                alpha2 = this.alpha2C,
                currency = this.currencyC,
                emoji = this.emojiC,
                latitude = this.latitudeC,
                longitude = this.longitudeC,
                name = this.nameC,
                numeric = this.numericC
            ),
            number = Number(length = this.numberLength, luhn = this.numberLuhn),
            prepaid = this.prepaid,
            scheme = this.scheme,
            type = this.type
        )
    }
}

fun BinInfoModel.toBinInfoEntity(): BinInfoEntity {
    return BinInfoEntity(
        binCode = this.binCode,
        bank = this.bank,
        brand = this.brand,
        alpha2C = this.country?.alpha2,
        currencyC = this.country?.currency,
        emojiC = this.country?.emoji,
        latitudeC = this.country?.latitude,
        longitudeC = this.country?.longitude,
        nameC = this.country?.name,
        numericC = this.country?.numeric,
        numberLength = this.number?.length,
        numberLuhn = this.number?.luhn,
        prepaid = this.prepaid,
        scheme = this.scheme,
        type = this.type
    )
}