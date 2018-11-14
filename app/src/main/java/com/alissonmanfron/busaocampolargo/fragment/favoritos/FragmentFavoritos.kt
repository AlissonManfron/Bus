package com.alissonmanfron.busaocampolargo.fragment.favoritos


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.detail.LinhaDetailActivity
import com.alissonmanfron.busaocampolargo.adapter.LinhasFavAdapter
import com.alissonmanfron.busaocampolargo.extensions.toast
import com.alissonmanfron.busaocampolargo.listener.FavoriteAddEvent
import com.alissonmanfron.busaocampolargo.listener.FavoriteRemoveEvent
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject

class FragmentFavoritos : Fragment(), FragFavoritosContract.LinhasView {

    // Variables
    override val presenter: FragFavoritosContract.LinhasPresenter by inject()
    private var adapter: LinhasFavAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Registra para receber eventos do bus
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Config recycler view
        setupRecyclerView()

        // Get instance presenter
        presenter.subscribe(this)

        // Get Linhas
        presenter.loadLinhas()

    }

    private fun setupRecyclerView() {
        rv_linhas_fav.layoutManager = LinearLayoutManager(activity)
        rv_linhas_fav.itemAnimator = DefaultItemAnimator()
        rv_linhas_fav.setHasFixedSize(true)
    }

    override fun setListLinhasObj(linhas: List<Linha>) {
        // Atualiza a lista
        activity?.runOnUiThread {
            rv_linhas_fav.visibility = View.VISIBLE
            adapter = LinhasFavAdapter(linhas) { linha: Linha, clickFavorite: Boolean ->
                if (clickFavorite) onClickFavoriteLinha(linha) else onClickLinha(linha)
            }
            rv_linhas_fav.adapter = adapter
        }
    }

    override fun setErrorLoadLinhas() {
        toast("Ocorreu um erro ao carregar as listas. Tente novamente!")
    }

    override fun showProgressBar() {
        activity?.runOnUiThread { pbLinhasFav.visibility = View.VISIBLE }
    }

    override fun hideProgressBar() {
        activity?.runOnUiThread { pbLinhasFav.visibility = View.GONE }
    }

    override fun navigateToLinhaDetail(linha: Linha) {
        val intent = Intent(activity, LinhaDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("linha", linha)
        intent.putExtra("linhaBundle", bundle)
        startActivity(intent)
    }

    override fun successRemoveFavorite(linha: Linha, msg: String) {
        // Show message
        toast(msg)
        // Trigger an event to refresh the list
        EventBus.getDefault().post(FavoriteRemoveEvent(linha))
        // Remove current Linha
        adapter?.removeLinha(linha)
    }

    override fun errorSetFavorite(msg: String) {
        toast(msg)
    }

    fun onClickLinha(linha: Linha) {
        presenter.onClickLinha(linha)
    }

    fun onClickFavoriteLinha(linha: Linha) {
        presenter.onClickFavoriteLinha(linha)
    }

    @Subscribe
    fun onRefresh(event: FavoriteAddEvent) {
        println("event = [${event.linha.isFavorite}]")
        presenter.loadLinhas()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
        // Cancela os eventos do bus
        EventBus.getDefault().unregister(this)
    }

}
