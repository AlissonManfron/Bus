package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FragLinhasInteractorImpl : FragLinhasContract.LinhasInteractor {

    override fun loadLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {

        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Load linhas from database
        Observable.fromCallable({ database?.gelAll() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null)
                        callback.onLoadSuccess(it)
                    else
                        callback.onLoadError()
                }, {
                    callback.onLoadError()
                })
    }

    override fun changeFavorite(linha: LinhaObj, callback: FragLinhasContract.LinhasInteractor.OnFavoriteFinishedListener) {

        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        Observable.fromCallable({ database?.updateFavorite(linha.cod, linha.isFavorite) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null)
                        callback.onFavoriteSuccess()
                    else
                        callback.onFavoriteError()
                }, {
                    callback.onFavoriteError()
                })
    }
}