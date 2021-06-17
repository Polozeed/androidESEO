package com.example.androideseo.ui.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R


class DescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, DescriptionActivity::class.java)
        }
    }
}
