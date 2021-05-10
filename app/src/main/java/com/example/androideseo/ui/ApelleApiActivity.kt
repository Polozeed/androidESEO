package com.example.androideseo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityApelleapiBinding
import com.example.androideseo.service.ServiceClient
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
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    val res = ServiceClient.instance.connexion(user_name,password)
                    LocalPreferences.getInstance(this@ApelleApiActivity).addTokenToHistory(res.token)
                    runOnUiThread{
                        Toast.makeText(this@ApelleApiActivity,  LocalPreferences.getInstance(this@ApelleApiActivity).getToken().toString(),
                                Toast.LENGTH_SHORT).show()
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