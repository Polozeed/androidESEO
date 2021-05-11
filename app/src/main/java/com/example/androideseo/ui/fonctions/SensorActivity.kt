package com.example.androideseo.ui.fonctions


import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.adapter.AdapterSensor
import com.example.androideseo.databinding.ActivitySensorBinding
import com.example.androideseo.service.ServiceInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer


class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivitySensorBinding // <-- Référence à notre ViewBinding
    private lateinit var sensorManager: SensorManager

    private val arr = arrayOf(
            SettingsItemSensor("Luminosité", 0.0f, R.drawable.light),
            SettingsItemSensor("Proximité", 0.0f, R.drawable.proximity),
            SettingsItemSensor("Gravité", 0.0f, R.drawable.planete),
            SettingsItemSensor("Accélération", 0.0f, R.drawable.speed)
    )

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SensorActivity::class.java)
        }
    }

    data class SettingsItemSensor(val name: String, var value: Float, val icon: Int) {}

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


        // Code executé toutes les 20 secondes
        fixedRateTimer("timer",false,0,20000) {
            //this@SensorActivity.runOnUiThread {
                CoroutineScope(Dispatchers.IO).launch {
                    runCatching {
                        val info = ServiceInformation.EnregInfo(
                                luminosite = arr[0].value.toLong(),
                                proximite = arr[1].value.toLong(),
                                gravite = arr[2].value.toLong(),
                                acceleration = arr[3].value.toLong()
                        )
                        val res = ServiceInformation.instance.enregInfo(info)
                    }
                }
            //}
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
        when(event.sensor.type) {
            Sensor.TYPE_LIGHT -> arr[0].value = event.values[0]
            Sensor.TYPE_PROXIMITY -> arr[1].value = event.values[0]
            Sensor.TYPE_GRAVITY-> arr[2].value = event.values[1]
            Sensor.TYPE_LINEAR_ACCELERATION-> arr[3].value = event.values[2]
            else ->  Toast.makeText(this, "Capteurs inexistants", Toast.LENGTH_SHORT).show()
        }
        //println("------------------------ type :" + event.sensor.type)
        //println("------------------------ type :" + event.values[0])
        binding.sensorList.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_UI)

        binding.sensorList.layoutManager = LinearLayoutManager(this)
        binding.sensorList.adapter = AdapterSensor(arr)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT))
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY))
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY))
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION))
    }
}
