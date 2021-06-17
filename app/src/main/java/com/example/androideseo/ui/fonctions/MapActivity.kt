package com.example.androideseo.ui.fonctions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityMapBinding
import java.util.*


class MapActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_LOCATION: Int = 999;
    private  val lateseo = 47.492884574915365;
    private  val longeseo = -0.5509639806591626;
    private lateinit var binding: ActivityMapBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MapActivity::class.java)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val animationbounce: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.bounce_animation)

        supportActionBar?.apply {
            setTitle(getString(R.string.activitymap))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMap.startAnimation(animationbounce);
        binding.buttonMap.setOnClickListener {
            this.requestPermission();
        }

        binding.buttonHistorique?.setOnClickListener {
            if (LocalPreferences.getInstance(this).nullHistory() == 0) {
                Toast.makeText(this, getString(R.string.histvide), Toast.LENGTH_SHORT).show()
            }else{
                startActivity(HistoryActivity.getStartIntent(this@MapActivity))
            }
        }

        binding.buttonGoogleMap.startAnimation(animationbounce);
        binding.buttonGoogleMap.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:")));
        }


        binding.buttonhist?.setOnClickListener {
            if (LocalPreferences.getInstance(this).nullHistory() == 0) {
                Toast.makeText(this, getString(R.string.histvide), Toast.LENGTH_SHORT).show()
            }else{
                startActivity(HistoryActivity.getStartIntent(this))
            }
        }
    }

    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_LOCATION
            )
        } else {
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue
                    getLocation()
                } else {
                    // Permission non accordé par l'utilisateur
                    Toast.makeText(this@MapActivity,getString(R.string.textacceptPerm),Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {
            val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager?
            locationManager?.run {
                locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)?.run {
                    geoCode(this)
                }
            }
        }
    }

    private fun geoCode(location: Location): MutableList<Address>? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        val locationText = findViewById<TextView>(R.id.textMap)
        if (results.isNotEmpty()) {
            locationText.text = results[0].getAddressLine(0)
            Toast.makeText(this@MapActivity,results[0].getAddressLine(0),Toast.LENGTH_LONG).show()
            LocalPreferences.getInstance(this).addToHistory(locationText.text.toString())

        }
        return results;
    }
}