package com.alissonmanfron.busaocampolargo.prefs

import android.content.SharedPreferences
import com.alissonmanfron.busaocampolargo.MyApplication

object Prefs {
    val PREF_ID = "bus"
    private fun prefs(): SharedPreferences {
        val context = MyApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setInt(flag: String, valor: Int) = prefs().edit().putInt(flag, valor).apply()
    fun getInt(flag: String) = prefs().getInt(flag, 0)
    fun setString(flag: String, valor: String) = prefs().edit().putString(flag, valor).apply()
    fun getString(flag: String) = prefs().getString(flag, "")
    var tabIdx: Int
        get() = getInt("tabIdx")
        set(value) = setInt("tabIdx", value)

    var versionCod: Int
        get() = getInt("versionCod")
        set(value) = setInt("versionCod", value)
}