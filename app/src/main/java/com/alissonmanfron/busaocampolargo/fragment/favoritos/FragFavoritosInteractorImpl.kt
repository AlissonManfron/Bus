package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class FragFavoritosInteractorImpl(var database: LinhaDao) : FragFavoritosContract.LinhasInteractor {

    override fun loadLinhas(callback: FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener) {

        // Simulate long request
        val subs = database.gelAllFavorites()
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

    override fun changeFavorite(linha: Linha, callback: FragFavoritosContract.LinhasInteractor.OnRemoveFavoriteFinishedListener) {

        // Simulate long request
        val subs = Observable.fromCallable { database.updateFavorite(linha.cod, linha.isFavorite) }
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