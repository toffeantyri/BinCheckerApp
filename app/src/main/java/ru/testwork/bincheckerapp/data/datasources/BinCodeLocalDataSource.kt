package ru.testwork.bincheckerapp.data.datasources

import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeLocalDataSource {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel?

    suspend fun getListBinCodeInfo(): List<BinInfoModel>

    suspend fun saveResultBinCodeInfo(binCodeModel: BinInfoModel)

}