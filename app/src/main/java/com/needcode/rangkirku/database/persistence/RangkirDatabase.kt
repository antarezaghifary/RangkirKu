package com.needcode.rangkirku.database.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WaybillEntity::class],
    exportSchema = false,
    //jika ada perubahan tabel ganti version +1
    version = 1
)
abstract class RangkirDatabase : RoomDatabase() {

    abstract fun waybillDao(): WaybillDao

    companion object {
        @Volatile
        private var instance: RangkirDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RangkirDatabase::class.java, "rangkir.db"
            )
                .allowMainThreadQueries()
                .build()
    }
}