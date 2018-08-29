package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class FragFavoritosInteractorImpl : FragFavoritosContract.LinhasInteractor {

    override fun loadLinhas(callback: FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener) {

        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        database?.gelAllFavorites()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            if (it != null) {
                callback.onLoadSuccess(it)
            } else {
                callback.onLoadError()
            }
        }, {
            callback.onLoadError()
        })
    }

    override fun changeFavorite(linha: LinhaObj, callback: FragFavoritosContract.LinhasInteractor.OnRemoveFavoriteFinishedListener) {
        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        Observable.fromCallable { database?.updateFavorite(linha.cod, linha.isFavorite) }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null) callback.onRemoveFavoriteSuccess() else callback.onFavoriteError()
                }, {
                    if (it !is ConcurrentModificationException)
                        callback.onFavoriteError()
                 })
    }
}