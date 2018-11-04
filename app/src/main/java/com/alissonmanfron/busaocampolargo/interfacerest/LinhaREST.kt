package com.alissonmanfron.busaocampolargo.interfacerest

import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaObj
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LinhaREST {

    @GET()
    fun getLinhas(): Call<List<LinhaObj>>

    @GET("{id}")
    fun getLinhaById(@Path("id") id: Long): Call<List<LinhaObj>>

}