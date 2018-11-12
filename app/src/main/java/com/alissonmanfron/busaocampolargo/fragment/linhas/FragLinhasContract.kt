package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.model.Version

interface FragLinhasContract {

    interface LinhasView {

        fun setListLinhasObj(linhas: List<Linha>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun successSetFavorite(linha: Linha, msg: String)

        fun errorSetFavorite(msg: String)

        fun navigateToLinhaDetail(linha: Linha)

    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onLoadSuccess(linhas: List<Linha>)
            fun onLoadError()
        }
        fun loadLinhas(callback: OnLoadFinishedListener)

        interface OnVersionFinishedListener {
            fun onLoadSuccess(version: Version)
            fun onLoadError()
        }
        fun loadVersion(callback: OnVersionFinishedListener)

        interface OnFavoriteFinishedListener {
            fun onFavoriteSuccess()
            fun onFavoriteError()
        }
        fun changeFavorite(linha: Linha, callback: OnFavoriteFinishedListener)

    }

    interface LinhasPresenter {

        fun loadVersion()

        fun loadLinhas()

        fun onDestroy()

        fun onClickLinha(linha: Linha)

        fun onClickFavoriteLinha(linha: Linha)

    }
}