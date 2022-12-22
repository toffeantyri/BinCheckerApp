package ru.testwork.bincheckerapp.data.repositories

import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeInfoRepository {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel

}