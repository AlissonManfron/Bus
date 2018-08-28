package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FragFavoritosInteractorImpl : FragFavoritosContract.LinhasInteractor{

    override fun loadLinhas(callback: FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener) {

        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        Observable.fromCallable({ database?.gelAllFavorites() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        callback.onLoadSuccess(it)
                    } else {
                        callback.onLoadError()
                    }
                }, {
                    callback.onLoadError()
                })
    }

    override fun changeFavorite(linha: LinhaObj, callback: FragFavoritosContract.LinhasInteractor.OnFavoriteFinishedListener) {
        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        Observable.fromCallable({ database?.updateFavorite(linha.cod, linha.isFavorite) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) callback.onRemoveFavoriteSuccess() else callback.onFavoriteError()
                }, {
                    callback.onFavoriteError()
                })
    }
}