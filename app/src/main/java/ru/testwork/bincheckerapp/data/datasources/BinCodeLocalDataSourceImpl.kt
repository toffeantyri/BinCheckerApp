package ru.testwork.bincheckerapp.data.datasources

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel
import ru.testwork.bincheckerapp.data.room.BinInfoDao
import ru.testwork.bincheckerapp.data.utils.toBinInfoEntity
import ru.testwork.bincheckerapp.data.utils.toBinInfoModel
import ru.testwork.bincheckerapp.presentation.TAG
import javax.inject.Inject

class BinCodeLocalDataSourceImpl @Inject constructor(private val dao: BinInfoDao) :
    BinCodeLocalDataSource {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel? {
        val result = dao.getById(code)
        Log.d(TAG, "LOCAL SOURCE : $result ")
        return result.toBinInfoModel()
    }

    override suspend fun getListBinCodeInfo(): Flow<List<BinInfoModel>> {
        return dao.getBinDataList().mapNotNull { list -> list.mapNotNull { it.toBinInfoModel() } }
    }

    override suspend fun saveResultBinCodeInfo(binCodeModel: BinInfoModel) {
        dao.insert(binCodeModel.toBinInfoEntity())
    }

    override suspend fun clearDB() {
        dao.clearDb()
    }
}