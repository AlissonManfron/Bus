package com.alissonmanfron.busaocampolargo.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "linha")
class LinhaObj(@PrimaryKey(autoGenerate = true) var id: Long?,
               @ColumnInfo(name = "cod") var cod: Int,
               @ColumnInfo(name = "name") var name: String,
               @ColumnInfo(name = "is_favorite") var isFavorite: Boolean,
               @ColumnInfo(name = "linhas_bairro") var linhasBairro: ArrayList<String>,
               @ColumnInfo(name = "linhas_terminal") var linhasTerminal: ArrayList<String>) {

    constructor(): this(null, 0,  "",false, ArrayList<String>(), ArrayList<String>())
}