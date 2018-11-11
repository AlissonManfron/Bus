package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaDao
import com.alissonmanfron.busaocampolargo.service.LinhaService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragLinhasInteractorImpl : FragLinhasContract.LinhasInteractor {

    var database: LinhaDao? = null

    // Get instance db
    init { database = AppDatabase.getInstance()?.linhaDao() }

    override fun loadLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {

        // Load linhas from database
        val subs = database?.gelAll()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null && it.size > 0)
                        callback.onLoadSuccess(it)
                    else
                        fetchLinhas(callback)
                }, {
                    fetchLinhas(callback)
                })
    }

    private fun fetchLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {
        val service = LinhaService()
        val subs = service.getLinhas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    if (it != null) {
                        val linhas = it
                        for (l in linhas) {
                            val suns2 = Observable.fromCallable { database?.insert(l) }
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        println("Salvo com sucesso!")
                                    }, {
                                        println("error")
                                    })
                        }

                        callback.onLoadSuccess(it)
                    } else
                        callback.onLoadError()
                }, {
                    callback.onLoadError()
                })
    }

    override fun changeFavorite(linha: Linha, callback: FragLinhasContract.LinhasInteractor.OnFavoriteFinishedListener) {

        // Simulate long request
        val subs = Observable.fromCallable { database?.updateFavorite(linha.cod, linha.isFavorite) }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null)
                        callback.onFavoriteSuccess()
                    else
                        callback.onFavoriteError()
                }, {
                    callback.onFavoriteError()
                })
    }
}