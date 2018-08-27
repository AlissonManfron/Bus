package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

class FragFavoritosPresenterImpl(private var linhasFavView: FragFavoritosContract.LinhasView?,
                                 private var linhasInteractor: FragFavoritosInteractorImpl) :
        FragFavoritosContract.LinhasPresenter {

    override fun loadLinhas() {
        linhasFavView?.showProgressBar()

        linhasInteractor.loadLinhas(object : FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: List<LinhaObj>) {
                linhasFavView?.hideProgressBar()
                linhasFavView?.setListLinhasObj(linhas)
            }

            override fun onLoadError() {
                linhasFavView?.hideProgressBar()
                linhasFavView?.setErrorLoadLinhas()
            }
        })
    }

    override fun onClickFavoriteLinha(linha: LinhaObj) {

    }

    override fun onClickLinha(linha: LinhaObj) {
    }

    override fun onDestroy() {
        linhasFavView = null
    }
}