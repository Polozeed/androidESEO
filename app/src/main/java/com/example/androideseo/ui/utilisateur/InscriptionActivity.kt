package com.example.androideseo.ui.utilisateur

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityInscriptionBinding

import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.app.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInscriptionBinding // <-- Référence à notre ViewBinding

    // Objet présent dans la liste (structure objet)
    data class User (var id : Int, var name : String, var content : String, var done : Boolean)
    data class UserApi ( var data : Array<User>)



    companion object {
        fun getStartIntent(context: ConnexionActivity): Intent {
            return Intent(context, InscriptionActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        binding = ActivityInscriptionBinding.inflate(layoutInflater)
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
            MaterialAlertDialogBuilder(this@InscriptionActivity)
                .setTitle(resources.getString(R.string.inscriptionmessage))
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    val user_name = user_name.text;
                    val password = password.text;
                    CoroutineScope(Dispatchers.IO).launch {
                        runCatching {
                            val res = ServiceClient.instance.inscription(user_name,password)
                            LocalPreferences.getInstance(this@InscriptionActivity).addTokenToHistory(res.token)
                            runOnUiThread{
                                Toast.makeText(this@InscriptionActivity, "Vous etes connecté",
                                    Toast.LENGTH_SHORT).show()
                                startActivity(MainActivity.getStartIntent(this@InscriptionActivity))
                            }
                        }
                    }
                }
                .show()



        }
    }





    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}