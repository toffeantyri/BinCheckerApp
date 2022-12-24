package ru.testwork.bincheckerapp.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.testwork.bincheckerapp.data.models.remote.BinInfoModel

interface ApiService {

    @GET("/{code}")
    suspend fun getBinInfoByCode(@Path("code") code: Int): BinInfoModel

}