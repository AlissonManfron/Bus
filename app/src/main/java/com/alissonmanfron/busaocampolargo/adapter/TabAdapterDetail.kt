package com.alissonmanfron.busaocampolargo.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alissonmanfron.busaocampolargo.fragment.diasUteis.DiasUteisFragment
import com.alissonmanfron.busaocampolargo.fragment.domingosFeriados.DomingosFeriadosFragment
import com.alissonmanfron.busaocampolargo.fragment.favoritos.FragmentFavoritos
import com.alissonmanfron.busaocampolargo.fragment.linhas.FragmentLinhas
import com.alissonmanfron.busaocampolargo.fragment.sabados.SabadosFragment
import com.alissonmanfron.busaocampolargo.fragment.valores.ValoresFragment

class TabAdapterDetail(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DiasUteisFragment()
            1 -> return SabadosFragment()
            2 -> return DomingosFeriadosFragment()
            else -> return DiasUteisFragment()
        }
    }

    // Título da Tab
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Dias Úteis"
            1 -> return "Sábados"
            2 -> return "Dom. e Feri"
            else -> return ""
        }
    }

    override fun getCount() = 3
}