package com.alissonmanfron.busaocampolargo.persistence

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
               @ColumnInfo(name = "linhas_bairro") var linhasBairro: ArrayList<String>,
               @ColumnInfo(name = "linhas_terminal") var linhasTerminal: ArrayList<String>) : Parcelable {

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
            })

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        id?.let { parcel?.writeLong(it) }
        parcel?.writeInt(cod)
        parcel?.writeString(name)
        parcel?.writeByte(if (isFavorite) 1 else 0)
        parcel?.writeList(linhasBairro)
        parcel?.writeList(linhasTerminal)
    }

    override fun describeContents() = 0

    constructor(): this(null, 0,  "",false, ArrayList<String>(), ArrayList<String>())

    companion object CREATOR : Parcelable.Creator<LinhaObj> {
        override fun createFromParcel(parcel: Parcel): LinhaObj {
            return LinhaObj(parcel)
        }

        override fun newArray(size: Int): Array<LinhaObj?> {
            return arrayOfNulls(size)
        }
    }
}