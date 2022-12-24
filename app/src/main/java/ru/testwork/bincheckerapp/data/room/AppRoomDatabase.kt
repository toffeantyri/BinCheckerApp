package ru.testwork.bincheckerapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.testwork.bincheckerapp.data.models.local.BinInfoEntity

@Database(
    version = 1,
    entities = [BinInfoEntity::class],
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getBinInfoDao(): BinInfoDao

    companion object {
        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {
            return if (database == null) {
                database =
                    Room.databaseBuilder(context, AppRoomDatabase::class.java, MAIN_LOCAL_DATABASE)
                        .fallbackToDestructiveMigration()
                        .build()
                database as AppRoomDatabase
            } else database as AppRoomDatabase
        }
    }

}
