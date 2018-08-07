package com.alissonmanfron.busaocampolargo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.item_linhas.view.*

class LinhasAdapter(private var linhas: List<Linha>,
                    val callback: (Linha, Boolean) -> Unit) :
        RecyclerView.Adapter<LinhasAdapter.LinhasViewHolder>() {

    override fun getItemCount() = this.linhas.size

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinhasViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_linhas, parent, false)
        // Retorna o ViewHolder que contém todas as views
        return LinhasViewHolder(view)
    }

    // Faz o bind para atualizar o valor das views com os dados do Carro
    override fun onBindViewHolder(holder: LinhasViewHolder, position: Int) {
        // Recupera o objeto carro
        val linha = linhas[position]
        val view = holder.itemView
        with(view) {
            // Atualiza os dados do carro
            txt_name_linha.text = linha.name
            // Adiciona o evento de clique na linha
            setOnClickListener { callback(linha, false) }

            tg_btn_favorite.setOnClickListener {
                if (linha.isFavorite) {
                    linha.isFavorite = false
                    tg_btn_favorite.setBackgroundResource(R.drawable.ic_star_white)
                } else {
                    linha.isFavorite = true
                    tg_btn_favorite.setBackgroundResource(R.drawable.ic_star_yellow)

                }
                callback(linha, true)
            }
        }
    }

    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class LinhasViewHolder(view: View) : RecyclerView.ViewHolder(view)
}