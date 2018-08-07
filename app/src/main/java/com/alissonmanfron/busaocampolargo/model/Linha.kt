package com.alissonmanfron.busaocampolargo.model

import android.os.Parcel
import android.os.Parcelable

class Linha(var cod: Int, var name: String, var isFavorite: Boolean,
            var linhasBairro: ArrayList<Int>, var linhasTerminal: ArrayList<Int>) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            arrayListOf<Int>().apply {
                parcel.readList(this, Int::class.java.classLoader)
            },
            arrayListOf<Int>().apply {
                parcel.readList(this, Int::class.java.classLoader)
            })

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cod)
        parcel.writeString(name)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeList(linhasBairro)
        parcel.writeList(linhasTerminal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Linha> {
        override fun createFromParcel(parcel: Parcel): Linha {
            return Linha(parcel)
        }

        override fun newArray(size: Int): Array<Linha?> {
            return arrayOfNulls(size)
        }
    }


}