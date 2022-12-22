package ru.testwork.bincheckerapp.data.datasources

import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeRemoteDataSource {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel

}