package com.newsapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.newsapp.R
import com.newsapp.ui.activity.news.BaseNewsActivity
import com.newsapp.ui.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        Handler(Looper.getMainLooper()).postDelayed(
                { startActivity(Intent(this@SplashActivity, BaseNewsActivity::class.java))
                finish()},
                1000)
    }
}