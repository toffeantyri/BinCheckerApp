package ru.testwork.bincheckerapp.data.datasources

import android.util.Log
import ru.testwork.bincheckerapp.TAG
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.room.BinInfoDao
import ru.testwork.bincheckerapp.data.utils.toBinInfoEntity
import ru.testwork.bincheckerapp.data.utils.toBinInfoModel
import javax.inject.Inject

class BinCodeLocalDataSourceImpl @Inject constructor(private val dao: BinInfoDao) :
    BinCodeLocalDataSource {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel? {
        val result = dao.getById(code)
        Log.d(TAG, "LOCAL SOURCE : $result ")
        return result.toBinInfoModel()
    }

    override suspend fun getListBinCodeInfo(): List<BinInfoModel> {
        val result = dao.getBinDataList()
        Log.d(TAG, "LOCAL SOURCE : $result ")
        return result.mapNotNull { it.toBinInfoModel() }
    }

    override suspend fun saveResultBinCodeInfo(binCodeModel: BinInfoModel) {
        dao.insert(binCodeModel.toBinInfoEntity())
    }

    override suspend fun clearDB() {
        dao.clearDb()
    }
}