package com.alissonmanfron.busaocampolargo.persistence.linhas

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.util.*

@Entity(tableName = "linha")
class LinhaObj(@PrimaryKey(autoGenerate = true) var id: Long?,
               @ColumnInfo(name = "cod") var cod: Int,
               @ColumnInfo(name = "name") var name: String,
               @ColumnInfo(name = "is_favorite") var isFavorite: Boolean,
               @ColumnInfo(name = "bairro_dias_uteis") var bairro_dias_uteis: ArrayList<String>,
               @ColumnInfo(name = "bairro_sabados") var bairro_sabados: ArrayList<String>,
               @ColumnInfo(name = "bairro_domingo_feriados") var bairro_domingo_feriados: ArrayList<String>,
               @ColumnInfo(name = "terminal_dias_uteis") var terminal_dias_uteis: ArrayList<String>,
               @ColumnInfo(name = "terminal_sabados") var terminal_sabados: ArrayList<String>,
               @ColumnInfo(name = "terminal_domingo_feriados") var terminal_domingo_feriados: ArrayList<String>) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            },
            arrayListOf<String>().apply {
                parcel.readList(this, String::class.java.classLoader)
            })

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        id?.let { parcel?.writeLong(it) }
        parcel?.writeInt(cod)
        parcel?.writeString(name)
        parcel?.writeByte(if (isFavorite) 1 else 0)
        parcel?.writeList(bairro_dias_uteis)
        parcel?.writeList(bairro_sabados)
        parcel?.writeList(bairro_domingo_feriados)
        parcel?.writeList(terminal_dias_uteis)
        parcel?.writeList(terminal_sabados)
        parcel?.writeList(terminal_domingo_feriados)
    }

    override fun describeContents() = 0

    constructor(): this(null, 0,  "",false, ArrayList<String>(), ArrayList<String>(),
            ArrayList<String>(), ArrayList<String>(), ArrayList<String>(), ArrayList<String>())

    companion object CREATOR : Parcelable.Creator<LinhaObj> {
        override fun createFromParcel(parcel: Parcel): LinhaObj {
            return LinhaObj(parcel)
        }

        override fun newArray(size: Int): Array<LinhaObj?> {
            return arrayOfNulls(size)
        }
    }
}