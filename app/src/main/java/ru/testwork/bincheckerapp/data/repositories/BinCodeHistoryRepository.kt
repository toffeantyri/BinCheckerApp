package ru.testwork.bincheckerapp.data.repositories

import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface BinCodeHistoryRepository {

    suspend fun getBinCodeListHistory(): List<BinInfoModel>

    suspend fun clearHistoryDb()

}