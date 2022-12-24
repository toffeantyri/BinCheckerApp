package ru.testwork.bincheckerapp.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.testwork.bincheckerapp.data.models.local.BinInfoEntity

@Dao
interface BinInfoDao {

    @Query("SELECT * FROM $TABLE_NAME_BININFO")
    fun getBinDataList(): Flow<List<BinInfoEntity>>

    @Upsert
    suspend fun insert(binInfoData: BinInfoEntity)

    @Query("SELECT * FROM $TABLE_NAME_BININFO WHERE bin_code = :code ")
    suspend fun getById(code: Int): BinInfoEntity?

    @Query("DELETE FROM $TABLE_NAME_BININFO")
    suspend fun clearDb()

}