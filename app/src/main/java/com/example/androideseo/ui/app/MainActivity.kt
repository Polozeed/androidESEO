package com.example.androideseo.ui.app

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityMainBinding
import com.example.androideseo.ui.app.MyApp.Companion.context
import com.example.androideseo.ui.fonctions.HistoryActivity
import com.example.androideseo.ui.fonctions.MapActivity
import com.example.androideseo.ui.fonctions.ParametreActivity
import com.example.androideseo.ui.fonctions.SensorActivity
import com.example.androideseo.ui.fragment.client.ListClientActivity
import com.example.androideseo.ui.utilisateur.ConnexionActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigationrail.NavigationRailView


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

        // Creation des animations sur les boutons
        val animationSimple: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.simple_animation)
        val animationfade: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.fadein_animation)

        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo?.setOnClickListener {
            // Utilisation d'animation de lancement d'activité
            val options = ActivityOptions.makeScaleUpAnimation(binding.logo, 10, 10, binding.logo!!.getWidth(), binding.logo!!.getHeight())
            startActivity(SensorActivity.getStartIntent(this@MainActivity), options.toBundle())
        }


        binding.navigationRail?.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_item_account -> {
                    startActivity(ConnexionActivity.getStartIntent(this@MainActivity))

                    true
                }
                R.id.menu_item_moon -> {
                    startActivity(ParametreActivity.getStartIntent(this@MainActivity))
                    true
                }
                R.id.menu_item_time -> {
                    startActivity(SensorActivity.getStartIntent(this@MainActivity))
                    true
                }
                R.id.menu_item_client -> {
                    startActivity(ListClientActivity.getStartIntent(this@MainActivity))
                    true
                }
                R.id.menu_item_map -> {
                    startActivity(MapActivity.getStartIntent(this@MainActivity))
                    true
                }
                else -> false
            }
        }
    };



}


