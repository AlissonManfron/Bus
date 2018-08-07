package com.alissonmanfron.busaocampolargo.fragment.linhas

import com.alissonmanfron.busaocampolargo.model.Linha

class FragLinhasPresenterImpl(private var linhasView: FragLinhasContract.LinhasView?,
                              private var linhasInteractor: FragLinhasInteractorImpl) :
                              FragLinhasContract.LinhasPresenter {

    override fun getLinhas() {

        linhasView?.showProgressBar()

        linhasInteractor.loadLinhas(object : FragLinhasContract.LinhasInteractor.OnLoadFinishedListener {
            override fun onSuccess(linhas: List<Linha>) {
                linhasView?.hideProgressBar()
                linhasView?.setListLinhas(linhas)
            }

            override fun onError() {
                linhasView?.hideProgressBar()
                linhasView?.setErrorLoadLinhas()
            }
        })
    }

    override fun onClickLinha(linha: Linha) {
        linhasView?.navigateToLinhaDetail(linha)
    }

    override fun onDestroy() {
        linhasView = null
    }

}