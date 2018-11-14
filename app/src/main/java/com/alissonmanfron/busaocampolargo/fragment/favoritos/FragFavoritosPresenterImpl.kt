package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.model.Linha

class FragFavoritosPresenterImpl(var linhasInteractor: FragFavoritosContract.LinhasInteractor?) :
        FragFavoritosContract.LinhasPresenter {
   
    override var view: FragFavoritosContract.LinhasView? = null

    override fun loadLinhas() {
        view?.showProgressBar()

        linhasInteractor?.loadLinhas(object : FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: List<Linha>) {
                view?.hideProgressBar()
                view?.setListLinhasObj(linhas)
            }

            override fun onLoadError() {
                view?.hideProgressBar()
                view?.setErrorLoadLinhas()
            }
        })
    }

    override fun onClickFavoriteLinha(linha: Linha) {
        linhasInteractor?.changeFavorite(linha, object : FragFavoritosContract.LinhasInteractor.OnRemoveFavoriteFinishedListener {
            override fun onRemoveFavoriteSuccess() {
                view?.successRemoveFavorite(linha, "${linha.name} foi removido dos favoritos!")
            }

            override fun onFavoriteError() {
                view?.errorSetFavorite("Não foi possível favoritar a linha ${linha.name}!")
            }
        })
    }

    override fun onClickLinha(linha: Linha) {
        view?.navigateToLinhaDetail(linha)
    }

    override fun subscribe(view: FragFavoritosContract.LinhasView) {
        this.view = view
    }

    override fun unSubscribe() {
        linhasInteractor = null
        view = null
    }

}