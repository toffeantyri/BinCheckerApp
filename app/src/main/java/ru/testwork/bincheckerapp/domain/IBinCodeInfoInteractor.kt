package ru.testwork.bincheckerapp.domain

import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface IBinCodeInfoInteractor {

    suspend fun getBinCodeInfo(code: Int): BinInfoModel

    suspend fun getHistoryListInfo(): Flow<List<BinInfoModel>>

    suspend fun clearHistory()

}