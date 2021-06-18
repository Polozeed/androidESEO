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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * TODO
 * Classe permettant la connexion de l'utilisateur
 */
class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding


    /**
     * TODO
     * Objet présent dans la liste
     * @property id
     * @property name
     * @property content
     * @property done
     */
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

        var user_name = findViewById<EditText>(R.id.login)
        var password = findViewById<EditText>(R.id.password)


        binding.btnReset?.setOnClickListener {
            user_name.setText("")
            password.setText("")
        }

        // set on-click listener
        binding.btnConnection?.setOnClickListener {
            MaterialAlertDialogBuilder(this@ConnexionActivity)
                .setTitle(resources.getString(R.string.messageconnexion))
                .setMessage(resources.getString(R.string.connexiondefaut))
                .setNeutralButton(resources.getString(R.string.decline)) { dialog, which ->
                }
                .setNegativeButton(resources.getString(R.string.inscription)) { dialog, which ->
                    startActivity(InscriptionActivity.getStartIntent(this@ConnexionActivity))
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    val user_name = user_name.text;
                    val password = password.text;
                    CoroutineScope(Dispatchers.IO).launch {
                        runCatching {
                            val res = ServiceClient.instance.connexion(user_name, password)
                            LocalPreferences.getInstance(this@ConnexionActivity).addTokenToHistory(res.token)
                            runOnUiThread {
                                Toast.makeText(this@ConnexionActivity, "Vous êtes connecté",
                                    Toast.LENGTH_SHORT).show()
                                startActivity(MainActivity.getStartIntent(this@ConnexionActivity))
                            }
                        }
                    }
                }
                .show()
        }

        binding.btnInscription?.setOnClickListener {
            startActivity(InscriptionActivity.getStartIntent(this@ConnexionActivity))
        }

        supportActionBar?.apply {
            setTitle(R.string.connexion)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}