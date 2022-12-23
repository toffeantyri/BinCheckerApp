package ru.testwork.bincheckerapp.data.repositories

import android.util.Log
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import javax.inject.Inject

class BinCodeHistoryRepositoryImpl @Inject constructor(
    private val localDataSource: BinCodeLocalDataSource
) : BinCodeHistoryRepository {

    override suspend fun getBinCodeListHistory(): List<BinInfoModel> {
        val result = localDataSource.getListBinCodeInfo()
        Log.d(TAG, "LIST REPO: $result ")
        return result
    }

    override suspend fun clearHistoryDb() {
        localDataSource.clearDB()
    }

}