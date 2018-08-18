package com.alissonmanfron.busaocampolargo.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.alissonmanfron.busaocampolargo.MyApplication

@Database(entities = [(LinhaObj::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun linhaDao(): LinhaDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getInstance(),
                               AppDatabase::class.java, "busaocampolargo.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}