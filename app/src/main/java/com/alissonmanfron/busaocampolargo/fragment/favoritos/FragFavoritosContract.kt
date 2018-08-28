package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

interface FragFavoritosContract {

    interface LinhasView {

        fun setListLinhasObj(linhas: MutableList<LinhaObj>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun successRemoveFavorite(linha: LinhaObj, msg: String)

        fun errorSetFavorite(msg: String)

        fun navigateToLinhaDetail(linha: LinhaObj)

    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onLoadSuccess(linhas: MutableList<LinhaObj>)
            fun onLoadError()
        }
        fun loadLinhas(callback: OnLoadFinishedListener)

        interface OnFavoriteFinishedListener {
            fun onRemoveFavoriteSuccess()
            fun onFavoriteError()
        }
        fun changeFavorite(linha: LinhaObj, callback: OnFavoriteFinishedListener)

    }

    interface LinhasPresenter {

        fun loadLinhas()

        fun onDestroy()

        fun onClickLinha(linha: LinhaObj)

        fun onClickFavoriteLinha(linha: LinhaObj)

    }
}