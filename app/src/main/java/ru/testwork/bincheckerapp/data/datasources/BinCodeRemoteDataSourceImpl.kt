package ru.testwork.bincheckerapp.data.datasources

import android.util.Log
import ru.testwork.bincheckerapp.data.api.ApiService
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.presentation.TAG
import javax.inject.Inject

class BinCodeRemoteDataSourceImpl @Inject constructor(private val api: ApiService) :
    BinCodeRemoteDataSource {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        Log.d(TAG, "REMOTE: $code ")
        val result = api.getBinInfoByCode(code).apply {
            binCode = code
        }
        Log.d(TAG, "REMOTE: $result ")
        return result
    }
}