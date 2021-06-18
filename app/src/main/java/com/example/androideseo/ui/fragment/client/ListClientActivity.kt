package com.example.androideseo.ui.fragment.client

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityHistoryBinding
import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.app.MainActivity
import com.example.androideseo.ui.fragment.ListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * TODO
 * Classe permettant le support du framgent liste client
 */
class ListClientActivity  : AppCompatActivity() {

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

        supportActionBar?.apply {
            setTitle(R.string.client)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}
