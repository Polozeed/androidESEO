package com.example.androideseo.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androideseo.R

class Adapter(private val deviceList: Array<String>, private val onClick: ((selectedDevice: String) -> Unit)? = null) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(value: String, onClick: ((selectedDevice: String) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.title).text = value
            if(onClick != null) {
                itemView.setOnClickListener {
                    onClick(value)
                }
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position], onClick)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

}