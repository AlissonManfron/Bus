package com.alissonmanfron.busaocampolargo.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface LinhaDao {

    @Query("SELECT * FROM linha")
    fun gelAll(): List<LinhaObj>

    @Insert(onConflict = REPLACE)
    fun insert(linha: LinhaObj)

    @Query("DELETE from linha")
    fun deleteAll()

    @Query("UPDATE linha SET is_favorite=:isFavorite WHERE id=:id")
    fun updateFavorite(id: Long, isFavorite: Boolean)
}