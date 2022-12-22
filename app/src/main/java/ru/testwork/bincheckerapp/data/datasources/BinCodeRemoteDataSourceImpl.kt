package ru.testwork.bincheckerapp.data.datasources

import ru.testwork.bincheckerapp.data.models.BinInfoModel

class BinCodeRemoteDataSourceImpl : BinCodeRemoteDataSource {

    override suspend fun getBinCodeInfo(code: Int): BinInfoModel {
        TODO("Not yet implemented")
    }
}