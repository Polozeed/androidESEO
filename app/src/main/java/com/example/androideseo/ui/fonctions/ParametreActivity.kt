package com.example.androideseo.ui.fonctions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.provider.Settings.ACTION_SETTINGS
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.adapter.AdapterParam
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
            SettingsItem("Paramètre Application", R.drawable.parametre) {
                // Action au clique
                startActivity(Intent(ACTION_SETTINGS))
            },
            SettingsItem("Paramètre Localisation", R.drawable.paramlocation) {
                // Action au clique
                startActivity(Intent(ACTION_LOCATION_SOURCE_SETTINGS))
            },
            SettingsItem("Carte", R.drawable.parammap) {
                // Action au clique
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.492884574915365,-0.5509639806591626")));

            },
            SettingsItem("Contacter nous", R.drawable.email) {
                // Action au clique
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:martincourierdemere@gmail.com " +
                        "?subject=" + Uri.encode("Indiquer le sujet de la demande") +
                        "&body=" + Uri.encode("Merci d'indiquer vos coordonnées afin que nous puissions revenir vers vous "))))

                },
            SettingsItem("Ville d'Angers", R.drawable.app) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.angers.fr/" )));
            }

        )

        binding = ActivityParametreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.parametre.layoutManager = LinearLayoutManager(this)
        binding.parametre.adapter = AdapterParam(arr)

        supportActionBar?.apply {
            setTitle(R.string.parametre)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}