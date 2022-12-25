package ru.testwork.bincheckerapp.data.repositories

import android.util.Log
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSource
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.presentation.TAG
import javax.inject.Inject

class BinCodeInfoRepositoryImpl @Inject constructor(
    private val remoteSource: BinCodeRemoteDataSource,
    private val localSource: BinCodeLocalDataSource
) : BinCodeInfoRepository {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        Log.d(TAG, "REPO : $code")
        val localResult = getFromDbByCode(code)
        return if (localResult != null) {
            Log.d(TAG, "REPO L: $localResult")
            localResult
        } else {
            val result = remoteSource.getBinCodeInfo(code)
            localSource.saveResultBinCodeInfo(result)
            Log.d(TAG, "REPO R: $result")
            result
        }
    }

    private suspend fun getFromDbByCode(code: Int): BinInfoModel? {
        val result = localSource.getBinCodeInfo(code)
        Log.d(TAG, "REPO DB: $result")
        return result
    }

}