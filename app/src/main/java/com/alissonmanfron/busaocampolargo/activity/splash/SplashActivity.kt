package com.alissonmanfron.busaocampolargo.activity.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.alissonmanfron.busaocampolargo.R
import com.alissonmanfron.busaocampolargo.activity.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        }, 1000)
    }
}
