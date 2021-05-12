package com.example.androideseo.ui.utilisateur

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityConnexionBinding
import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.fragment.client.ListClientActivity
import com.example.androideseo.ui.app.MainActivity
import com.example.androideseo.ui.app.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding// <-- Référence à notre ViewBinding

    // Objet présent dans la liste (structure objet)
    data class User (var id : Int, var name : String, var content : String, var done : Boolean)
    data class UserApi ( var data : Array<User>)



    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ConnexionActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityConnexionBinding.inflate(layoutInflater)
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
                        val res = ServiceClient.instance.connexion(user_name, password)
                        LocalPreferences.getInstance(this@ConnexionActivity).addTokenToHistory(res.token)
                        runOnUiThread {
                            Toast.makeText(this@ConnexionActivity, "Vous etes connecté",
                                    Toast.LENGTH_SHORT).show()
                            startActivity(MainActivity.getStartIntent(this@ConnexionActivity))
                        }
                    }
                }
            
        }

        binding.testliste?.setOnClickListener {
            startActivity(ListClientActivity.getStartIntent(this@ConnexionActivity))
        }

        binding.btnInscription?.setOnClickListener {
            startActivity(InscriptionActivity.getStartIntent(this@ConnexionActivity))
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}