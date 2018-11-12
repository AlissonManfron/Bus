package com.alissonmanfron.busaocampolargo.interfacerest

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.model.Version
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface LinhaREST {

    @GET("api/master/data")
    fun getLinhas(): Flowable<List<Linha>>

    @GET("api/master/version")
    fun getVersion(): Single<Version>

}