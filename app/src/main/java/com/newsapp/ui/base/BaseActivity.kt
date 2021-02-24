package com.newsapp.ui.base

import android.R
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    var mProgressBar: ProgressBar? = null

    fun showProgressBar() {
        if (mProgressBar == null) {
            val layout = RelativeLayout(this)
            mProgressBar = ProgressBar(this, null, R.attr.progressBarStyleLarge)
            val params = RelativeLayout.LayoutParams(100, 100)
            params.addRule(RelativeLayout.CENTER_IN_PARENT)
            layout.addView(mProgressBar, params)
        }
        mProgressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        mProgressBar?.visibility = View.GONE
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        mProgressBar?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mProgressBar?.visibility = View.GONE
    }

}