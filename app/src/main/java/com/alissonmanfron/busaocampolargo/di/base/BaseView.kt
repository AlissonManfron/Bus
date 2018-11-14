package com.alissonmanfron.busaocampolargo.di.base

/**
 * View
 */
interface BaseView<out T : BasePresenter<*>> {

    val presenter: T

}