package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaDao
import com.alissonmanfron.busaocampolargo.prefs.Prefs
import com.alissonmanfron.busaocampolargo.service.LinhaService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragLinhasInteractorImpl : FragLinhasContract.LinhasInteractor {

    var database: LinhaDao? = null

    // Get instance db
    init { database = AppDatabase.getInstance()?.linhaDao() }


    override fun loadVersion(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {
        val service = LinhaService()
        val subs = service.getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    if (it != null) {
                        val cod = Prefs.versionCod
                        if (cod != it.cod) {
                            Prefs.versionCod = it.cod
                            fetchLinhas(callback)
                        }
                    } else
                        callback.onLoadError()
                }, {
                    callback.onLoadError()
                })
    }

    override fun loadLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {
        fetchLocalLinhas(callback)
    }

    private fun fetchLocalLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {
        // Load linhas from database
        val subs2 = database?.gelAll()
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
                        val suns2 = Observable.fromCallable { database?.deleteAll() }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    for (l in linhas) {
                                        Observable.fromCallable { database?.insert(l) }
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                                                    callback.onLoadSuccess(linhas)
                                                }, {
                                                    callback.onLoadError()
                                                })
                                    }
                                }, {
                                    println("error")
                                })

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