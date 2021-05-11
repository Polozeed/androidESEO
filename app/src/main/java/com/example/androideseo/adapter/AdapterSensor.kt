package com.example.androideseo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androideseo.R
import com.example.androideseo.ui.fonctions.SensorActivity

class AdapterSensor(private val deviceList: Array<SensorActivity.SettingsItemSensor>) : RecyclerView.Adapter<AdapterSensor.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(sensor: SensorActivity.SettingsItemSensor) {
            itemView.findViewById<TextView>(R.id.sensor).text= sensor.name
            itemView.findViewById<TextView>(R.id.value).text= sensor.value.toString()
            itemView.findViewById<ImageView>(R.id.imagesensor).setImageResource(sensor.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position])
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}



