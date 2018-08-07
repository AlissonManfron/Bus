package com.alissonmanfron.busaocampolargo.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alissonmanfron.busaocampolargo.fragment.favoritos.FragmentFavoritos
import com.alissonmanfron.busaocampolargo.fragment.linhas.FragmentLinhas

class TabAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FragmentFavoritos()
            1 -> return FragmentLinhas()
            2 -> return FragmentFavoritos()
            else -> return FragmentFavoritos()
        }
    }

    // Título da Tab
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Favoritos"
            1 -> return "Linhas"
            2 -> return "Tab 3"
            else -> return ""
        }
    }

    override fun getCount() = 3
}