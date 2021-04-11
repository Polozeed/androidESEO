package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_SETTINGS
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
                binding.buttonhist?.setOnClickListener {
                    if (LocalPreferences.getInstance(this).nullHistory() == 0) {
                        Toast.makeText(this,"Historique Vide", Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(HistoryActivity.getStartIntent(this@MainActivity))
                    }
                }

        binding.settings?.setOnClickListener {
            startActivity(ParametreActivity.getStartIntent(this@MainActivity))

        }

        binding.buttonLoc?.setOnClickListener {
            startActivity(MapActivity.getStartIntent(this@MainActivity))
        }
    };
}