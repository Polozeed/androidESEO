package com.example.androideseo.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.data.AdapterHist
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityEastereggsBinding


class EastereggsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEastereggsBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, EastereggsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eastereggs)

        binding = ActivityEastereggsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle(getString(R.string.trollpage))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}