package ru.testwork.bincheckerapp.domain

import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface IBinCodeInfoInteractor {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel

}