package com.alissonmanfron.busaocampolargo.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.linhas.Converters
import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaDao

@Database(entities = [(Linha::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun linhaDao(): LinhaDao
}