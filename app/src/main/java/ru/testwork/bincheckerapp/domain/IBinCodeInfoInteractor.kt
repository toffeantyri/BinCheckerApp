package ru.testwork.bincheckerapp.domain

import ru.testwork.bincheckerapp.data.models.BinInfoModel

interface IBinCodeInfoInteractor {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel

}