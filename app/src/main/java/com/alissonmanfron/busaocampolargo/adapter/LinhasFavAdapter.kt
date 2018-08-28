package com.alissonmanfron.busaocampolargo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import kotlinx.android.synthetic.main.item_linhas.view.*

class LinhasFavAdapter(private var linhas: MutableList<LinhaObj>,
                       private val callback: (LinhaObj, Boolean) -> Unit) :
        RecyclerView.Adapter<LinhasFavAdapter.LinhasFavViewHolder>() {

    override fun getItemCount() = this.linhas.size

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinhasFavViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_linhas, parent, false)
        // Retorna o ViewHolder que contém todas as views
        return LinhasFavViewHolder(view)
    }

    // Faz o bind para atualizar o valor das views com os dados do Carro
    override fun onBindViewHolder(holder: LinhasFavViewHolder, position: Int) {
        // Recupera o objeto carro
        val linha = linhas[position]
        val view = holder.itemView
        with(view) {
            // Atualiza os dados do carro
            txt_cod_linha.text = linha.cod.toString()
            txt_name_linha.text = linha.name

            tg_btn_favorite.setBackgroundResource(if (linha.isFavorite) R.drawable.ic_star_yellow else R.drawable.ic_star_white)

            // Adiciona o evento de clique na linha
            setOnClickListener { callback(linha, false) }

            tg_btn_favorite.setOnClickListener {
                linha.isFavorite = false
                callback(linha, true)
            }
        }
    }

    fun onChangeBgButtomFavorite(linha: LinhaObj) {
        for (l in linhas) {
            if (l.id == linha.id) {
                l.isFavorite = linha.isFavorite
                notifyDataSetChanged()
            }
        }
    }

    fun removeLinha(linha: LinhaObj) {
        for (l in linhas) {
            if (l.id == linha.id) {
                linhas.remove(l)
                notifyDataSetChanged()
            }
        }
    }

    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class LinhasFavViewHolder(view: View) : RecyclerView.ViewHolder(view)
}