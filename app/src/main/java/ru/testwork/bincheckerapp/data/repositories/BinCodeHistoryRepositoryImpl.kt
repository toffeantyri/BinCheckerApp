package ru.testwork.bincheckerapp.data.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.presentation.TAG
import javax.inject.Inject

class BinCodeHistoryRepositoryImpl @Inject constructor(
    private val localDataSource: BinCodeLocalDataSource
) : BinCodeHistoryRepository {

    override suspend fun getBinCodeListHistory(): Flow<List<BinInfoModel>> {
        val result = localDataSource.getListBinCodeInfo()
        Log.d(TAG, "LIST REPO: $result ")
        return result
    }

    override suspend fun clearHistoryDb() {
        localDataSource.clearDB()
    }

}