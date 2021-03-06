package com.alissonmanfron.busaocampolargo.activity.main

import android.os.Bundle
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.base.BaseActivity
import com.alissonmanfron.busaocampolargo.enuns.Screen
import com.alissonmanfron.busaocampolargo.extensions.setupToolbar

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Config Toolbar
        setupToolbar(R.id.toolbar)

        // Config View Pager Tabs
        setupViewPagerTabsMain()

    }
}
