package ru.testwork.bincheckerapp.data.repositories

import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeHistoryRepository {

    suspend fun getBinCodeListHistory(): Flow<List<BinInfoModel>>

    suspend fun clearHistoryDb()

}