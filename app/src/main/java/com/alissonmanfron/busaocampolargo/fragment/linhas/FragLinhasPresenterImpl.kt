package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha

class FragLinhasPresenterImpl(var linhasInteractor: FragLinhasContract.LinhasInteractor?) :
        FragLinhasContract.LinhasPresenter {

    override var view: FragLinhasContract.LinhasView? = null

    override fun loadVersion() {
        linhasInteractor?.loadVersion(object : FragLinhasContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onLoadSuccess(linhas: List<Linha>) {

            }

            override fun onLoadError() {

            }
        })
    }

    override fun loadLinhas() {

        view?.showProgressBar()

        linhasInteractor?.loadLinhas(object : FragLinhasContract.LinhasInteractor.OnLoadFinishedListener {
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

    override fun onClickLinha(linha: Linha) {
        view?.navigateToLinhaDetail(linha)
    }

    override fun onClickFavoriteLinha(linha: Linha) {

        linhasInteractor?.changeFavorite(linha, object : FragLinhasContract.LinhasInteractor.OnFavoriteFinishedListener {
            override fun onFavoriteSuccess() {
                view?.successSetFavorite(linha,
                        if (linha.isFavorite) {
                            "${linha.name} foi adicionado ao seus favoritos!"
                        } else
                            "${linha.name} foi removido dos favoritos!")
            }

            override fun onFavoriteError() {
                view?.errorSetFavorite("Não foi possível favoritar a linha ${linha.name}!")
            }
        })
    }

    override fun subscribe(view: FragLinhasContract.LinhasView) {
        this.view = view
    }

    override fun unSubscribe() {
        linhasInteractor = null
        view = null
    }


}