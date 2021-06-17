package com.example.androideseo.ui.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R

class DescriptionAPIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descriptionapi)
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, DescriptionAPIActivity::class.java)
        }
    }
}