package com.example.kasirapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kasirapp.data.dao.KasirDao
import com.example.kasirapp.data.entity.*

@Database(entities = [Food::class, Beverage::class, User::class, Meja::class],
    version = 8,
    exportSchema = false
)
abstract class KasirDatabase : RoomDatabase() {
    abstract fun getKasirDao(): KasirDao

    companion object{
        @Volatile
        private var INSTANCE: KasirDatabase? = null

        fun getDatabase(context: Context): KasirDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KasirDatabase::class.java,
                    "kasir"
                ).fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}