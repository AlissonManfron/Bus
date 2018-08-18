package com.alissonmanfron.busaocampolargo.activity.detail

import android.os.Bundle
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.base.BaseActivity
import com.alissonmanfron.busaocampolargo.extensions.setupToolbar
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

class LinhaDetailActivity : BaseActivity() {
    private val linha by lazy { intent.getBundleExtra("linhaBundle").getParcelable<LinhaObj>("linha") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linha_detail)

        setupToolbar(R.id.toolbar, linha.name, true)

    }
}
