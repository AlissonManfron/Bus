package com.alissonmanfron.busaocampolargo.persistence.linhas

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.alissonmanfron.busaocampolargo.model.Linha
import io.reactivex.Flowable

@Dao
interface LinhaDao {

    @Query("SELECT * FROM linha")
    fun gelAll(): Flowable<MutableList<Linha>>

    @Query("SELECT * FROM linha WHERE is_favorite = 1")
    fun gelAllFavorites(): Flowable<MutableList<Linha>>

    @Insert(onConflict = REPLACE)
    fun insert(linha: Linha)

    @Insert(onConflict = REPLACE)
    fun insertAll(linha: List<Linha>)

    @Query("DELETE from linha")
    fun deleteAll()

    @Query("UPDATE linha SET is_favorite=:isFavorite WHERE cod=:cod")
    fun updateFavorite(cod: Int, isFavorite: Boolean)
}