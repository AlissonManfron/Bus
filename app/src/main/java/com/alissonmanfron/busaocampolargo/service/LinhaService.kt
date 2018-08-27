package com.alissonmanfron.busaocampolargo.service

import com.alissonmanfron.busaocampolargo.interfacerest.LinhaREST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LinhaService {
    private val BASE_URL = "192.168.43.140:8080/BusaoCampoLargo/linhas/"
    private var service: LinhaREST

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(LinhaREST::class.java)
    }

    // Busca uma lista de carros
    fun getLinhas() = service.getLinhas()

    fun getLinhaById(id: Long) = service.getLinhaById(id)
}