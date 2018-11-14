package com.alissonmanfron.busaocampolargo.fragment.linhas


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.detail.LinhaDetailActivity
import com.alissonmanfron.busaocampolargo.adapter.LinhasAdapter
import com.alissonmanfron.busaocampolargo.extensions.toast
import com.alissonmanfron.busaocampolargo.listener.FavoriteAddEvent
import com.alissonmanfron.busaocampolargo.listener.FavoriteRemoveEvent
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.fragment_linhas.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import android.view.MenuInflater
import org.koin.android.ext.android.inject


class FragmentLinhas : Fragment(), FragLinhasContract.LinhasView {

    // Get instance presenter by Koin
    override val presenter: FragLinhasContract.LinhasPresenter by inject()
    private var adapter: LinhasAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Registra para receber eventos do bus
        EventBus.getDefault().register(this)


        setHasOptionsMenu(true)
    }

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
        presenter.subscribe(this)

        // Load version
        presenter.loadVersion()

        // Get Linhas
        presenter.loadLinhas()

    }

    private fun setupRecyclerView() {
        rv_linhas.layoutManager = LinearLayoutManager(activity)
        rv_linhas.itemAnimator = DefaultItemAnimator()
        rv_linhas.setHasFixedSize(true)
    }

    override fun setListLinhasObj(linhas: List<Linha>) {
        // Atualiza a lista
        activity?.runOnUiThread {
            rv_linhas.visibility = View.VISIBLE
            adapter = LinhasAdapter(linhas) { linha: Linha, clickFavorite: Boolean ->
                if (clickFavorite) onClickFavoriteLinha(linha) else onClickLinha(linha)
            }
            rv_linhas.adapter = adapter
        }
    }

    override fun showProgressBar() {
        activity?.runOnUiThread { pbLinhas.visibility = View.VISIBLE }
    }

    override fun hideProgressBar() {
        activity?.runOnUiThread { pbLinhas.visibility = View.GONE }
    }

    override fun successSetFavorite(linha: Linha, msg: String) {
        // Trigger an event to refresh the list
        EventBus.getDefault().post(FavoriteAddEvent(linha))
        // Update buttom favorite background
        adapter?.onChangeBgButtomFavorite(linha)
        // Show message
        toast(msg)
    }

    override fun errorSetFavorite(msg: String) {
        toast(msg)
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

    fun onClickLinha(linha: Linha) {
        presenter.onClickLinha(linha)
    }

    fun onClickFavoriteLinha(linha: Linha) {
        presenter.onClickFavoriteLinha(linha)
    }

    @Subscribe
    fun onRefresh(event: FavoriteRemoveEvent) {
        println("event = [${event.linha.isFavorite}]")
        presenter.loadLinhas()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_frag_linhas, menu)

        val mSearch = menu?.findItem(R.id.action_search)

        val mSearchView = mSearch?.actionView as SearchView
        mSearchView.queryHint = "Search"

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter?.filter?.filter(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
        // Cancela os eventos do bus
        EventBus.getDefault().unregister(this)
    }
}
