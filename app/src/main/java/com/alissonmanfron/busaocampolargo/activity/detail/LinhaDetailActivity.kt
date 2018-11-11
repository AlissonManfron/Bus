package com.alissonmanfron.busaocampolargo.activity.detail

import android.os.Bundle
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.base.BaseActivity
import com.alissonmanfron.busaocampolargo.extensions.setupToolbar
import com.alissonmanfron.busaocampolargo.extensions.toast
import com.alissonmanfron.busaocampolargo.model.Linha
import kotlinx.android.synthetic.main.activity_linha_detail.*

class LinhaDetailActivity : BaseActivity() {
    private val linha by lazy { intent.getBundleExtra("linhaBundle").getParcelable<Linha>("linha") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linha_detail)

        // Config Toolbar
        setupToolbar(R.id.toolbar, linha.name, true)

        // Config View Pager Tabs
        setupViewPagerTabsDetail()

        fab.setOnClickListener { toast("Change..") }
    }
}
