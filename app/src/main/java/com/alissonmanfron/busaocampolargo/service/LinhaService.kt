package com.alissonmanfron.busaocampolargo.service

import com.alissonmanfron.busaocampolargo.interfacerest.LinhaREST
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LinhaService {
    private val BASE_URL = "https://raw.githubusercontent.com/AlissonManfron/"
    private var service: LinhaREST

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create(LinhaREST::class.java)
    }

    // Busca uma lista de carros
    fun getLinhas() = service.getLinhas()

    // Busca a versão corrente da lista de linhas
    fun getVersion() = service.getVersion()
}