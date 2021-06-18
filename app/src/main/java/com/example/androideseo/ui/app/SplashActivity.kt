package com.example.androideseo.ui.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        LocalPreferences.getInstance(MyApp.context!!).deleteToken()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /**
         * Lancement du slashActivity durant 4 seconde puis startActvity Main
         */

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }, 4000)
    }
}