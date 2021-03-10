package com.ahmedtawfik.moviesapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : BaseActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        progressBar.visibility = View.VISIBLE

        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)

    }
}