package ru.testwork.bincheckerapp.domain

import android.util.Log
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepository
import javax.inject.Inject

class IBinCodeInfoInteractorImpl @Inject constructor(private val binCodeRepo: BinCodeInfoRepository) :
    IBinCodeInfoInteractor {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        Log.d(TAG, "Interactor: $code")
        val result = binCodeRepo.getBinCodeInfo(code)
        Log.d(TAG, "Interactor: $result")
        return result
    }


}