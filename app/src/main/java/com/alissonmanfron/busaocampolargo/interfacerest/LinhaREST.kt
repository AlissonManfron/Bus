package com.alissonmanfron.busaocampolargo.interfacerest

import com.alissonmanfron.busaocampolargo.model.Linha
import io.reactivex.Flowable
import retrofit2.http.GET

interface LinhaREST {

    @GET("api/master/data.json")
    fun getLinhas(): Flowable<List<Linha>>

}