package com.alissonmanfron.busaocampolargo

import android.support.multidex.MultiDexApplication
import android.util.Log

class MyApplication: MultiDexApplication() {
    private val TAG = "MyApplication"

    // Chamado quando o Android criar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        // Salva a instância para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: MyApplication? = null

        fun getInstance(): MyApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    // Chamado quando o Android finalizar o processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "MyApplication.onTerminate()")
    }
}