package com.example.androideseo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.androideseo.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Lancement du slashActivity durant 4 seconde puis startActvity Main
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartIntent(this))
        }, 4000)
    }
}