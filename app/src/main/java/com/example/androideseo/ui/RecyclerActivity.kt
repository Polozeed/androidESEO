package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.data.Adapter
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, RecyclerActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = Adapter(LocalPreferences.getInstance(this).getHistory() as Array<String>) { item ->
            Toast.makeText(this@RecyclerActivity, "Connexion à $item", Toast.LENGTH_SHORT).show()
        }
        binding.buttonTest.setOnClickListener {
            if(LocalPreferences.getInstance(this).getHistory() != null){
                Toast.makeText(this, LocalPreferences.getInstance(this).getHistory().toString(), Toast.LENGTH_SHORT).show()
            }
        }




    }
}