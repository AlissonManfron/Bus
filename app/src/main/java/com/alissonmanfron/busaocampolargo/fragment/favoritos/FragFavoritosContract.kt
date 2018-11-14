package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.di.base.BasePresenter
import com.alissonmanfron.busaocampolargo.di.base.BaseView
import com.alissonmanfron.busaocampolargo.model.Linha

interface FragFavoritosContract {

    interface LinhasView : BaseView<LinhasPresenter> {

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

    interface LinhasPresenter : BasePresenter<LinhasView> {

        fun loadLinhas()

        fun onClickLinha(linha: Linha)

        fun onClickFavoriteLinha(linha: Linha)

    }
}