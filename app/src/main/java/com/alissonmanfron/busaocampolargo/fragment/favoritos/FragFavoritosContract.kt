package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.model.Linha

interface FragFavoritosContract {

    interface LinhasView {

        fun setListLinhasObj(linhas: List<Linha>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun successRemoveFavorite(linha: Linha, msg: String)

        fun errorSetFavorite(msg: String)

        fun navigateToLinhaDetail(linha: Linha)

    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onLoadSuccess(linhas: List<Linha>)
            fun onLoadError()
        }
        fun loadLinhas(callback: OnLoadFinishedListener)

        interface OnRemoveFavoriteFinishedListener {
            fun onRemoveFavoriteSuccess()
            fun onFavoriteError()
        }
        fun changeFavorite(linha: Linha, callback: OnRemoveFavoriteFinishedListener)

    }

    interface LinhasPresenter {

        fun loadLinhas()

        fun onDestroy()

        fun onClickLinha(linha: Linha)

        fun onClickFavoriteLinha(linha: Linha)

    }
}