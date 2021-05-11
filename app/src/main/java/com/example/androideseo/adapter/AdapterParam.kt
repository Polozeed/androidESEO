package com.example.androideseo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androideseo.ui.fonctions.ParametreActivity
import com.example.androideseo.R

class AdapterParam(private val deviceList: Array<ParametreActivity.SettingsItem>, private val onClick: ((selectedDevice: ParametreActivity.SettingsItem) -> Unit)? = null) : RecyclerView.Adapter<AdapterParam.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(param: ParametreActivity.SettingsItem, onClick: ((selectedDevice: ParametreActivity.SettingsItem) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.param).text= param.name
            itemView.findViewById<ImageView>(R.id.imageparam).setImageResource(param.icon)
            itemView.findViewById<ConstraintLayout>(R.id.layoutID).setOnClickListener{
                param.onClick.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.param, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(deviceList[position], onClick)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}



