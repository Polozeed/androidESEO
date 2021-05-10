package com.example.androideseo.ui

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        // Creation des animations sur les boutons
        val animationSimple: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.simple_animation)
        val animationfade: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.fadein_animation)

        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
                // ajout d'une animation sur un layout
                binding.buttonhist?.startAnimation(animationfade);
                binding.buttonhist?.setOnClickListener {
                    if (LocalPreferences.getInstance(this).nullHistory() == 0) {
                        Toast.makeText(this, getString(R.string.histvide), Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(HistoryActivity.getStartIntent(this@MainActivity))
                    }
                }

        binding.settings?.startAnimation(animationSimple);
        binding.settings?.setOnClickListener {
            // Utilisation d'animation
            val options = ActivityOptions.makeScaleUpAnimation(binding.settings, 10, 10, binding.settings!!.getWidth(), binding.settings!!.getHeight())
            startActivity(ParametreActivity.getStartIntent(this@MainActivity), options.toBundle())
        }

        binding.buttonLoc?.startAnimation(animationfade);
        binding.buttonLoc?.setOnClickListener {
            // Utilisation d'animation de lancement d'activité
            val options = ActivityOptions.makeScaleUpAnimation(binding.settings, 10, 10, binding.settings!!.getWidth(), binding.settings!!.getHeight())
            startActivity(MapActivity.getStartIntent(this@MainActivity), options.toBundle())
        }


        binding.buttonApi?.setOnClickListener {
            startActivity(ApelleApiActivity.getStartIntent(this@MainActivity))
        }

        binding.logo?.setOnClickListener {
            // Utilisation d'animation de lancement d'activité
            val options = ActivityOptions.makeScaleUpAnimation(binding.logo, 10, 10, binding.logo!!.getWidth(), binding.logo!!.getHeight())
            startActivity(SensorActivity.getStartIntent(this@MainActivity), options.toBundle())
        }
    };
}


