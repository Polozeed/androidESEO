package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.recyclical.datasource.emptyDataSource
import com.example.androideseo.R
import com.example.androideseo.data.AdapterApi
import com.example.androideseo.databinding.ActivityApelleapiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ApelleApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApelleapiBinding // <-- Référence à notre ViewBinding

    // Objet présent dans la liste (structure objet)
    data class User (var id : Int, var name : String, var content : String, var done : Boolean)
    data class UserApi ( var data : Array<User>)



    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ApelleApiActivity::class.java)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_apelleapi)

        binding = ActivityApelleapiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = emptyDataSource()


                findViewById<Button>(R.id.apelleApi).setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        runCatching {
                            val id = 1
                            val arrStatus = ApiService.instance.gettest()

                            runOnUiThread {
                                //dataSource.addAll(arrStatus.data.toList())
                                Toast.makeText(
                                    this@ApelleApiActivity,
                                    "Résultat de l'appel réseau  :  " + arrStatus.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                /*binding = ActivityApelleapiBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.recyclerapi.layoutManager = LinearLayoutManager(this)
                binding.recyclerapi.adapter = AdapterApi(dataSource)

                 */

            }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}