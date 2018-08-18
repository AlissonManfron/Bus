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

        // Create fake objects lines
        val l = LinhaObj(null, 101, "Itaqui", false, arrayListOf("1534375279", "1534375279"), arrayListOf("1534375279", "1534375279"))
        val l2 = LinhaObj(null, 106, "Campo do Meio", false, arrayListOf("1534375279", "1534375279"), arrayListOf("1534375279", "1534375279"))
        val l3 = LinhaObj(null, 109, "Rivabem", false, arrayListOf("1534375279", "1534375279"), arrayListOf("1534375279", "1534375279"))

        // Create fake list
        val linhas = arrayListOf(l, l2, l3)

        // Simulate long request
        Observable.fromCallable({ database?.gelAll() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null && it.isNotEmpty()) {
                        callback.onLoadSuccess(it)
                    } else {
                        for (linha in linhas) {
                            database?.insert(linha)
                        }
                        callback.onLoadSuccess(linhas)
                    }
                }, {
                    callback.onLoadError()
                })

    }

    override fun changeFavorite(linha: LinhaObj, callback: FragLinhasContract.LinhasInteractor.OnFavoriteFinishedListener) {

        // Get instance db
        val database = AppDatabase.getInstance()?.linhaDao()

        // Simulate long request
        Observable.fromCallable({ database?.updateFavorite(linha.id!!, linha.isFavorite) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) callback.onFavoriteSuccess() else callback.onFavoriteError()
                }, {
                    callback.onFavoriteError()
                })

    }


}