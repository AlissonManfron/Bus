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
import com.alissonmanfron.busaocampolargo.listener.FavoritoEvent
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import kotlinx.android.synthetic.main.fragment_favoritos.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class FragmentFavoritos : Fragment(), FragFavoritosContract.LinhasView {

    // Variables
    private var presenter: FragFavoritosContract.LinhasPresenter? = null
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
        presenter = FragFavoritosPresenterImpl(this, FragFavoritosInteractorImpl())

        // Get Linhas
        presenter?.loadLinhas()

    }

    private fun setupRecyclerView() {
        rv_linhas_fav.layoutManager = LinearLayoutManager(activity)
        rv_linhas_fav.itemAnimator = DefaultItemAnimator()
        rv_linhas_fav.setHasFixedSize(true)
    }

    override fun setListLinhasObj(linhas: List<LinhaObj>) {
        // Atualiza a lista
        activity?.runOnUiThread({
            rv_linhas_fav.visibility = View.VISIBLE
            adapter = LinhasFavAdapter(linhas) { linha: LinhaObj, clickFavorite: Boolean ->
                if (clickFavorite) onClickFavoriteLinha(linha) else onClickLinha(linha)
            }
            rv_linhas_fav.adapter = adapter
        })
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

//    override fun successSetFavorite(linha: LinhaObj, msg: String) {
//        adapter?.onChangeBgButtomFavorite(linha)
//        toast(msg)
//    }
//
//    override fun errorSetFavorite(msg: String) {
//        toast(msg)
//    }

    override fun navigateToLinhaDetail(linha: LinhaObj) {
        val intent = Intent(activity, LinhaDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("linha", linha)
        intent.putExtra("linhaBundle", bundle)
        startActivity(intent)
    }

    fun onClickLinha(linha: LinhaObj) {
        presenter?.onClickLinha(linha)
    }

    fun onClickFavoriteLinha(linha: LinhaObj) {
        presenter?.onClickFavoriteLinha(linha)
    }

    @Subscribe
    open fun onRefresh(event: FavoritoEvent) {
        println("event = [${event.linha.isFavorite}]")
        presenter?.loadLinhas()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        // Cancela os eventos do bus
        EventBus.getDefault().unregister(this)
    }

}
