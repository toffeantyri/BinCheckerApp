package ru.testwork.bincheckerapp.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.repositories.BinCodeHistoryRepository
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepository
import ru.testwork.bincheckerapp.presentation.TAG
import javax.inject.Inject

class IBinCodeInfoInteractorImpl @Inject constructor(
    private val binCodeRepo: BinCodeInfoRepository,
    private val binCodeHistoryRepo: BinCodeHistoryRepository
) :
    IBinCodeInfoInteractor {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        Log.d(TAG, "Interactor: $code")
        val result = binCodeRepo.getBinCodeInfo(code)
        Log.d(TAG, "Interactor: $result")
        return result
    }

    override suspend fun getHistoryListInfo(): Flow<List<BinInfoModel>> {
        val result = binCodeHistoryRepo.getBinCodeListHistory()
        Log.d(TAG, "Interactor: $result")
        return result
    }

    override suspend fun clearHistory() {
        binCodeHistoryRepo.clearHistoryDb()
    }


}