package com.example.androideseo.ui.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.databinding.ActivityDescriptionBinding
import com.example.androideseo.databinding.ActivityDescriptionapiBinding

class DescriptionAPIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionapiBinding // <-- Référence à notre ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descriptionapi)

        binding = ActivityDescriptionapiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnappli.setOnClickListener {
            startActivity(DescriptionActivity.getStartIntent(this@DescriptionAPIActivity))
        }

        supportActionBar?.apply {
            setTitle(R.string.Description)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, DescriptionAPIActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}