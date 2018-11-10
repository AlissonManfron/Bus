package com.alissonmanfron.busaocampolargo.fragment.favoritos

import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaObj

class FragFavoritosPresenterImpl(private var linhasFavView: FragFavoritosContract.LinhasView?,
                                 private var linhasInteractor: FragFavoritosContract.LinhasInteractor) :
        FragFavoritosContract.LinhasPresenter {

    override fun loadLinhas() {
        linhasFavView?.showProgressBar()

        linhasInteractor.loadLinhas(object : FragFavoritosContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: List<Linha>) {
                linhasFavView?.hideProgressBar()
                linhasFavView?.setListLinhasObj(linhas)
            }

            override fun onLoadError() {
                linhasFavView?.hideProgressBar()
                linhasFavView?.setErrorLoadLinhas()
            }
        })
    }

    override fun onClickFavoriteLinha(linha: Linha) {
        linhasInteractor.changeFavorite(linha, object : FragFavoritosContract.LinhasInteractor.OnRemoveFavoriteFinishedListener {
            override fun onRemoveFavoriteSuccess() {
                linhasFavView?.successRemoveFavorite(linha, "${linha.name} foi removido dos favoritos!")
            }

            override fun onFavoriteError() {
                linhasFavView?.errorSetFavorite("Não foi possível favoritar a linha ${linha.name}!")
            }
        })
    }

    override fun onClickLinha(linha: Linha) {
        linhasFavView?.navigateToLinhaDetail(linha)
    }

    override fun onDestroy() {
        linhasFavView = null
    }
}