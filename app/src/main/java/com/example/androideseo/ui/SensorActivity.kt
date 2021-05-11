package com.example.androideseo.ui


import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.databinding.ActivitySensorBinding


class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivitySensorBinding // <-- Référence à notre ViewBinding
    private lateinit var sensorManager: SensorManager

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SensorActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        binding = ActivitySensorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle("capteur")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        findViewById<TextView>(R.id.capteurLuminosite).text = event.values[0].toString()
    }

    override fun onResume() {
        super.onResume()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
            SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
