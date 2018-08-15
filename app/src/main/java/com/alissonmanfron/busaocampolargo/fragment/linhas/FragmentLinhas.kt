package com.alissonmanfron.busaocampolargo.fragment.linhas


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.detail.LinhaDetailActivity
import com.alissonmanfron.busaocampolargo.adapter.LinhasAdapter
import com.alissonmanfron.busaocampolargo.extensions.toast
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.fragment_linhas.*

class FragmentLinhas : Fragment(), FragLinhasContract.LinhasView {

    // Variables
    private lateinit var presenter: FragLinhasContract.LinhasPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linhas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Config recycler view
        setupRecyclerView()

        // Get instance presenter
        presenter = FragLinhasPresenterImpl(this, FragLinhasInteractorImpl())

        // Get Linhas
        presenter.getLinhas()

    }

    private fun setupRecyclerView() {
        rv_linhas.layoutManager = LinearLayoutManager(activity)
        rv_linhas.itemAnimator = DefaultItemAnimator()
        rv_linhas.setHasFixedSize(true)
    }

    override fun setListLinhas(linhas: List<Linha>) {
        // Atualiza a lista
        activity?.runOnUiThread({
            // Atualiza a lista
            rv_linhas.visibility = View.VISIBLE
            rv_linhas.adapter = LinhasAdapter(linhas) { linha: Linha, clickFavorite: Boolean ->
                if (clickFavorite) onClickFavoriteLinha(linha) else onClickLinha(linha)
            }
        })
    }

    override fun showProgressBar() {
        activity?.runOnUiThread { pbLinhas.visibility = View.VISIBLE }
    }

    override fun hideProgressBar() {
        activity?.runOnUiThread { pbLinhas.visibility = View.GONE }
    }

    override fun showMessageFavorite(name: String) {
        toast("$name foi adicionado ao seus favoritos!")
    }

    override fun navigateToLinhaDetail(linha: Linha) {
        val intent = Intent(activity, LinhaDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("linha", linha)
        intent.putExtra("linhaBundle", bundle)
        startActivity(intent)
    }

    override fun setErrorLoadLinhas() {
        toast("Ocorreu um erro ao carregar as listas. Tente novamente!")
    }

    override fun onClickLinha(linha: Linha) {
        presenter.onClickLinha(linha)
    }

    fun onClickFavoriteLinha(linha: Linha) {
        //presenter.onClickLinha(linha, isFavorite)
        toast("${linha.name} is favorite ? ${linha.isFavorite}")
    }

    override fun navigateToFragFavorite() {
        val viewPager = activity?.findViewById<ViewPager>(R.id.viewPager)
        viewPager?.setCurrentItem(0,true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
