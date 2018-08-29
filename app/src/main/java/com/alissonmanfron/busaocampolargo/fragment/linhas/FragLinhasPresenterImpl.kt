package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

class FragLinhasPresenterImpl(private var linhasView: FragLinhasContract.LinhasView?,
                              private var linhasInteractor: FragLinhasContract.LinhasInteractor) :
        FragLinhasContract.LinhasPresenter {

    override fun loadLinhas() {

        linhasView?.showProgressBar()

        linhasInteractor.loadLinhas(object : FragLinhasContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: List<LinhaObj>) {
                linhasView?.hideProgressBar()
                linhasView?.setListLinhasObj(linhas)
            }

            override fun onLoadError() {
                linhasView?.hideProgressBar()
                linhasView?.setErrorLoadLinhas()
            }
        })
    }

    override fun onClickLinha(linha: LinhaObj) {
        linhasView?.navigateToLinhaDetail(linha)
    }

    override fun onClickFavoriteLinha(linha: LinhaObj) {

        linhasInteractor.changeFavorite(linha, object : FragLinhasContract.LinhasInteractor.OnFavoriteFinishedListener {
            override fun onFavoriteSuccess() {
                linhasView?.successSetFavorite(linha,
                        if (linha.isFavorite) {
                            "${linha.name} foi adicionado ao seus favoritos!"
                        } else
                            "${linha.name} foi removido dos favoritos!")
            }

            override fun onFavoriteError() {
                linhasView?.errorSetFavorite("Não foi possível favoritar a linha ${linha.name}!")
            }
        })
    }

    override fun onDestroy() {
        linhasView = null
    }

}