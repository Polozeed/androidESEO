package com.example.androideseo.ui.fragment.client

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.androideseo.R
import com.example.androideseo.databinding.ActivityHistoryBinding
import com.example.androideseo.ui.fragment.ListFragment

class ListClientActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ListClientActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listclient)
        ListFragment
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
    }

}