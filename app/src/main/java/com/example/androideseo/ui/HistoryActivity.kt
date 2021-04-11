package com.example.androideseo.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androideseo.R
import com.example.androideseo.data.AdapterHist
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityHistoryBinding


class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding // <-- Référence à notre ViewBinding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }


    data class SettingsItem(val name: String, val icon: Int, val onClick: (() -> Unit)) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parametre)

        val valHist = LocalPreferences.getInstance(this@HistoryActivity).getHistory()
        val res : Array<String> = valHist!!.toTypedArray()


        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.history.layoutManager = LinearLayoutManager(this)
        binding.history.adapter = AdapterHist(res)

        binding.buttonDelete.setOnClickListener {
            LocalPreferences.getInstance(this).deleteAllHistory()
            startActivity(MainActivity.getStartIntent(this@HistoryActivity))
        }

        supportActionBar?.apply {
            setTitle(R.string.w)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}


