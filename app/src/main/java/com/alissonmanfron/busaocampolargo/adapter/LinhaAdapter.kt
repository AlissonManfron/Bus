package com.alissonmanfron.busaocampolargo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.extensions.toDate
import com.alissonmanfron.busaocampolargo.extensions.toDateString
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.item_linhas.view.*

class LinhasAdapter(private var linhas: List<Linha>,
                    private val callback: (Linha, Boolean) -> Unit) :
        RecyclerView.Adapter<LinhasAdapter.LinhasViewHolder>(), Filterable {


    private var linhasCopy: List<Linha> = linhas
    private var itemFilter = ItemFilter()

    override fun getFilter(): Filter {
        return itemFilter
    }

    override fun getItemCount() = this.linhas.size

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinhasViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_linhas, parent, false)
        // Retorna o ViewHolder que contém todas as views
        return LinhasViewHolder(view)
    }

    // Faz o bind para atualizar o valor das views com os dados da Linha
    override fun onBindViewHolder(holder: LinhasViewHolder, position: Int) {
        // Recupera o objeto carro
        val linha = linhas[position]
        val view = holder.itemView
        with(view) {
            // Atualiza os dados da linha
            txt_cod_linha.text = linha.cod.toString()
            txt_name_linha.text = linha.name

            val dt1 = linha.terminal_dias_uteis[0].toDate() // Todo: Pegar a data do próximo onibus
            val dt2 = linha.bairro_dias_uteis[0].toDate() // Todo: Pegar a data do próximo onibus
            //val rs = dt1.between(dt2) // mostra a diferença entre duas datas
            val dt3 = dt1.toDateString()
            val dt4 = dt2.toDateString()
            txt_next_hour_terminal.text = dt3
            txt_next_hour_bairro.text = dt4

            tg_btn_favorite.setBackgroundResource(if (linha.isFavorite) R.drawable.ic_star_yellow else R.drawable.ic_star_white)

            // Adiciona o evento de clique na linha
            setOnClickListener { callback(linha, false) }

            tg_btn_favorite.setOnClickListener {
                linha.isFavorite = !linha.isFavorite
                callback(linha, true)
            }
        }
    }

    fun onChangeBgButtomFavorite(linha: Linha) {
        for (l in linhas) {
            if (l.cod == linha.cod) {
                l.isFavorite = linha.isFavorite
                notifyDataSetChanged()
            }
        }
    }

    private inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResults = Filter.FilterResults()


            if (constraint.isNotEmpty()) {
                //CHANGE TO UPPER
                val string = constraint.toString().toUpperCase()

                //HOLD FILTERS WE FIND
                val foundFilters = ArrayList<Linha>()

                //ITERATE CURRENT LIST
                for (user in linhas) {
                    //SEARCH
                    if (user.name.toUpperCase().contains(string)) {
                        //ADD IF FOUND
                        foundFilters.add(user)
                    }
                }

                //SET RESULTS TO FILTER LIST
                filterResults.count = foundFilters.size
                filterResults.values = foundFilters
            } else {
                //NO ITEM FOUND.LIST REMAINS INTACT
                filterResults.count = filterResults.count
                filterResults.values = linhasCopy
            }

            //RETURN RESULTS
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            setLinhas(results.values as List<Linha>)
            notifyDataSetChanged()
        }
    }

    private fun setLinhas(list: List<Linha>) {
        this.linhas = list
    }

    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class LinhasViewHolder(view: View) : RecyclerView.ViewHolder(view)
}