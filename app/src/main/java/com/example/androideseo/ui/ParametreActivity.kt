package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.provider.Settings.ACTION_SETTINGS
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.data.AdapterParam
import com.example.androideseo.databinding.ActivityParametreBinding

class ParametreActivity : AppCompatActivity() {


    private lateinit var binding: ActivityParametreBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ParametreActivity::class.java)
        }
    }


    data class SettingsItem(val name: String, val icon: Int, val onClick: (() -> Unit)) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parametre)

        val arr = arrayOf(
            SettingsItem(getString(R.string.paramApp), R.drawable.parametre) {
                // Action au clique
                startActivity(Intent(ACTION_SETTINGS))
            },
            SettingsItem(getString(R.string.paramLoc), R.drawable.paramlocation) {
                // Action au clique
                startActivity(Intent(ACTION_LOCATION_SOURCE_SETTINGS))
            },
            SettingsItem(getString(R.string.carte), R.drawable.parammap) {
                // Action au clique
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.geoEseo))));

            },
            SettingsItem(getString(R.string.mailEtu), R.drawable.email) {
                // Action au clique
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse(getString(R.string.mailtoMoi) +
                        "?subject=" + Uri.encode(getString(R.string.testapp)) +
                        "&body=" + Uri.encode(getString(R.string.salutprof)))))

                },
            SettingsItem(getString(R.string.siteEseotext), R.drawable.logo) {
                // Action au clique
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.siteEseo) )));
            }

        )

        binding = ActivityParametreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.parametre.layoutManager = LinearLayoutManager(this)
        binding.parametre.adapter = AdapterParam(arr)

        supportActionBar?.apply {
            setTitle(R.string.w)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}