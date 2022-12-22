package ru.testwork.bincheckerapp.data.datasources

import ru.testwork.bincheckerapp.data.models.BinInfoModel

class BinCodeLocalDataSourceImpl : BinCodeLocalDataSource {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        TODO("Not yet implemented")
    }

    override suspend fun getListBinCodeInfo(): List<BinInfoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun saveResultBinCodeInfo(binCodeModel: BinInfoModel) {
        TODO("Not yet implemented")
    }
}