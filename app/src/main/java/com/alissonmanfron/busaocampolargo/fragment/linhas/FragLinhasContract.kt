package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha

interface FragLinhasContract {

    interface LinhasView {

        fun onClickLinha(linha: Linha)

        fun setListLinhas(linhas: List<Linha>)

        fun setErrorLoadLinhas()

        fun showProgressBar()

        fun hideProgressBar()

        fun showMessageFavorite(name: String)

        fun navigateToLinhaDetail(linha: Linha)
    }

    interface LinhasInteractor {

        interface OnLoadFinishedListener {
            fun onSuccess(linhas: List<Linha>)
            fun onError()
        }

        fun loadLinhas(callback: OnLoadFinishedListener)

    }

    interface LinhasPresenter {

        fun getLinhas()

        fun onDestroy()

        fun onClickLinha(linha: Linha)

    }
}