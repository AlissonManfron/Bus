package com.alissonmanfron.busaocampolargo.di.base

/**
 * Presenter
 */
interface BasePresenter<V> {

    fun subscribe(view: V)

    fun unSubscribe()

    var view : V?

}