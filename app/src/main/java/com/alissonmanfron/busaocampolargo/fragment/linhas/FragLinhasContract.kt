package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

interface FragLinhasContract {

    interface LinhasView {

        fun setListLinhasObj(linhas: List<LinhaObj>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun successSetFavorite(linha: LinhaObj, msg: String)

        fun errorSetFavorite(msg: String)

        fun navigateToLinhaDetail(linha: LinhaObj)

        fun navigateToFragFavorite()
    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onLoadSuccess(linhas: List<LinhaObj>)
            fun onLoadError()
        }
        fun loadLinhas(callback: OnLoadFinishedListener)

        interface OnFavoriteFinishedListener {
            fun onFavoriteSuccess()
            fun onFavoriteError()
        }
        fun changeFavorite(linha: LinhaObj, callback: OnFavoriteFinishedListener)

    }

    interface LinhasPresenter {

        fun getLinhas()

        fun onDestroy()

        fun onClickLinha(linha: LinhaObj)

        fun onClickFavoriteLinha(linha: LinhaObj)

    }
}