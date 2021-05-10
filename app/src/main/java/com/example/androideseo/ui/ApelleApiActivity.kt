package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.databinding.ActivityApelleapiBinding
import com.example.androideseo.service.ApiService
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
        setContentView(R.layout.activity_main)

        binding = ActivityApelleapiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get reference to all views
        var user_name = findViewById<EditText>(R.id.login)
        var password = findViewById<EditText>(R.id.password)


        binding.btnReset?.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            user_name.setText("")
            password.setText("")
        }

        // set on-click listener
        binding.btnConnection?.setOnClickListener {
            val user_name = user_name.text;
            val password = password.text;
            println("je suis ici ")
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    println("je suis ici 2")
                    val arrStatus = ApiService.instance.postconnexion(user_name,password)
                    println("je suis ici 3")
                    runOnUiThread{

                        Toast.makeText(this@ApelleApiActivity, "Résultat de l'appel réseau" + arrStatus.toString(), Toast.LENGTH_SHORT).show()
                        System.out.println("--------------")
                    }
                }
            }


        }
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}