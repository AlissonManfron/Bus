package com.alissonmanfron.busaocampolargo.activity.base

import android.annotation.SuppressLint
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.adapter.TabAdapter
import com.alissonmanfron.busaocampolargo.adapter.TabAdapterDetail
import com.alissonmanfron.busaocampolargo.enuns.Screen
import com.alissonmanfron.busaocampolargo.prefs.Prefs
import kotlinx.android.synthetic.main.activity_linha_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() { //, NavigationView.OnNavigationItemSelectedListener {

    fun setupViewPagerTabsMain() {
        // Configura o ViewPager + Tabs
        // As variáveis viewPager e tabLayout são geradas automaticamente pelo Kotlin Extensions
        viewPager.offscreenPageLimit = 3

        viewPager.adapter = TabAdapter(this, supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
        // Cor branca no texto (o fundo azul é definido no layout)
        val cor = ContextCompat.getColor(this, R.color.white)
        tabLayout.setTabTextColors(cor, cor)

        // Salva e Recupera a última Tab acessada.
        val tabIdx = Prefs.tabIdx

        viewPager.currentItem = tabIdx
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(page: Int) {
                // Salva o índice da página/tab selecionada
                Prefs.tabIdx = page
            }
        })
    }


    fun setupViewPagerTabsDetail() {
        // Configura o ViewPager + Tabs
        // As variáveis viewPager e tabLayout são geradas automaticamente pelo Kotlin Extensions
        viewPager_detail.offscreenPageLimit = 3

        viewPager_detail.adapter = TabAdapterDetail(this, supportFragmentManager)

        tabLayout_detail.setupWithViewPager(viewPager_detail)
        // Cor branca no texto (o fundo azul é definido no layout)
        val cor = ContextCompat.getColor(this, R.color.white)
        tabLayout_detail.setTabTextColors(cor, cor)

        // Salva e Recupera a última Tab acessada.
        val tabIdx = Prefs.tabIdx

        viewPager_detail.currentItem = tabIdx
        viewPager_detail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(page: Int) {
                // Salva o índice da página/tab selecionada
                //Prefs.tabIdx = page
            }
        })
    }
//
//    // Trata o evento do Navigation Drawer
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_item_home -> {
//                toast("Clicou em Favoritos")
//                viewPager.setCurrentItem(0, true)
//            }
//            R.id.nav_item_opcao -> {
//                toast("clicou em Linhas")
//                viewPager.setCurrentItem(1, true)
//            }
//        }
//        // Fecha o menu depois de tratar o evento
//        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
//        drawer.closeDrawer(GravityCompat.START)
//        return true
//    }
}