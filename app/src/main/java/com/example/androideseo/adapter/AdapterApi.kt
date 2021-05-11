package com.example.androideseo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.DataSource
import com.example.androideseo.R
import com.example.androideseo.ui.utilisateur.ConnexionActivity


class AdapterApi(private val res: DataSource<Any>) : RecyclerView.Adapter<AdapterApi.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(param: ConnexionActivity.UserApi) {
            itemView.findViewById<TextView>(R.id.param).text= param.toString()
            itemView.findViewById<ConstraintLayout>(R.id.title).setOnClickListener{
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.param, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}

