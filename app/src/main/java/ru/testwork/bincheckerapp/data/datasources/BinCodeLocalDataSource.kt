package ru.testwork.bincheckerapp.data.datasources

import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeLocalDataSource {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel?

    suspend fun getListBinCodeInfo(): Flow<List<BinInfoModel>>

    suspend fun saveResultBinCodeInfo(binCodeModel: BinInfoModel)

    suspend fun clearDB()

}