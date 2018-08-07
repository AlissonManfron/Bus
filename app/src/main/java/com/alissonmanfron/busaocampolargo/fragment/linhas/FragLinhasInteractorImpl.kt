package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FragLinhasInteractorImpl : FragLinhasContract.LinhasInteractor {


    override fun loadLinhas(callback: FragLinhasContract.LinhasInteractor.OnLoadFinishedListener) {

        val l = Linha(101, "Itaqui", false, arrayListOf(10,20), arrayListOf(10,20))
        val l2 = Linha(106,"Campo do Meio", false, arrayListOf(12,20), arrayListOf(10,20))
        val l3 = Linha(109,"Rivabem", false, arrayListOf(17,20), arrayListOf(10,20))

        val linhas = arrayListOf(l, l2, l3)

        // Simulate long request
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onSuccess(linhas)
                })
    }
}