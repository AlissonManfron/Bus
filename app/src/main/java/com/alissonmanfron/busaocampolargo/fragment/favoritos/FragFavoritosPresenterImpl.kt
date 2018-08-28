package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

class FragFavoritosPresenterImpl(private var linhasFavView: FragFavoritosContract.LinhasView?,
                                 private var linhasInteractor: FragFavoritosInteractorImpl) :
        FragFavoritosContract.LinhasPresenter {

    override fun loadLinhas() {
        linhasFavView?.showProgressBar()

        linhasInteractor.loadLinhas(object : FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: MutableList<LinhaObj>) {
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
        linhasInteractor.changeFavorite(linha, object : FragFavoritosContract.LinhasInteractor.OnFavoriteFinishedListener {
            override fun onRemoveFavoriteSuccess() {
                linhasFavView?.successRemoveFavorite(linha, "${linha.name} foi removido dos favoritos!")
            }

            override fun onFavoriteError() {
                linhasFavView?.errorSetFavorite("Não foi possível favoritar a linha ${linha.name}!")
            }
        })
    }

    override fun onClickLinha(linha: LinhaObj) {
        linhasFavView?.navigateToLinhaDetail(linha)
    }

    override fun onDestroy() {
        linhasFavView = null
    }
}