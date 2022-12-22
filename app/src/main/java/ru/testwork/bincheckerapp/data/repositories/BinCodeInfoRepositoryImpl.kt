package ru.testwork.bincheckerapp.data.repositories

import android.util.Log
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSource
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import javax.inject.Inject

class BinCodeInfoRepositoryImpl @Inject constructor(
    private val remoteSource: BinCodeRemoteDataSource,
    private val localSource: BinCodeLocalDataSource
) : BinCodeInfoRepository {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        Log.d(TAG, "REPO : $code")
        return if (!checkCodeIsAlreadySaved(code)) {
            val result = remoteSource.getBinCodeInfo(code)
            Log.d(TAG, "REPO R: $result")
            result
        } else {
            val result = localSource.getBinCodeInfo(code)
            Log.d(TAG, "REPO L: $result")
            result
        }
    }

    private suspend fun checkCodeIsAlreadySaved(code: Int): Boolean {
        //todo
        return false

    }


}