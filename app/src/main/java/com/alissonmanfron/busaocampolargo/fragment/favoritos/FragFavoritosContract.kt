package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

interface FragFavoritosContract {

    interface LinhasView {

        fun setListLinhasObj(linhas: List<LinhaObj>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun navigateToLinhaDetail(linha: LinhaObj)

    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onLoadSuccess(linhas: List<LinhaObj>)
            fun onLoadError()
        }
        fun loadLinhas(callback: OnLoadFinishedListener)

    }

    interface LinhasPresenter {

        fun loadLinhas()

        fun onDestroy()

        fun onClickLinha(linha: LinhaObj)

        fun onClickFavoriteLinha(linha: LinhaObj)

    }
}