package com.example.androideseo.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androideseo.R
import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.app.MyApp
import com.example.androideseo.ui.fragment.client.ListClientActivity


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * TODO
 * Classe permettant affichage d'un client apres clic sur liste client
 */
class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }



    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = ServiceClient.instance.getUser(args.userId)

            activity?.runOnUiThread {
                view?.apply {
                    val identity = findViewById<TextView>(R.id.identity_user)
                    identity.text = user.identity()

                    findViewById<Button>(R.id.deleteclient)?.setOnClickListener {
                        CoroutineScope(Dispatchers.IO).launch {
                            runCatching {
                                val res = ServiceClient.instance.deleteUser(args.userId)
                            }
                        }
                        Toast.makeText(
                            MyApp.context, "Element Supprimé", Toast.LENGTH_SHORT).show()
                        startActivity(ListClientActivity.getStartIntent(this.context))

                    }

                    findViewById<Button>(R.id.modifierclient)?.setOnClickListener {
                        val builder: AlertDialog.Builder =
                            android.app.AlertDialog.Builder(this.context)
                        builder.setTitle("Modifier identifiant")
                        val input = EditText(this.context)
                        input.setHint(identity.text)
                        input.inputType = InputType.TYPE_CLASS_TEXT
                        input.inputType = InputType.TYPE_CLASS_TEXT
                        builder.setView(input)

                        builder.setPositiveButton(
                            "OK",
                            DialogInterface.OnClickListener { dialog, which ->

                                CoroutineScope(Dispatchers.IO).launch {
                                    runCatching {
                                        user.login= input.text.toString()
                                        val res = ServiceClient.instance.editUser(args.userId,user)
                                    }
                                }
                                Toast.makeText(MyApp.context, user.identity_user(), Toast.LENGTH_SHORT).show()
                                startActivity(ListClientActivity.getStartIntent(this.context))
                            })
                        builder.setNegativeButton(
                            "Retour",
                            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

                        builder.show()
                    }


                    findViewById<Button>(R.id.modifiermdp)?.setOnClickListener {
                        val builder: AlertDialog.Builder =
                                android.app.AlertDialog.Builder(this.context)
                        builder.setTitle("Modifier Mot de Passe")
                        val input = EditText(this.context)
                        input.setHint(identity.text)
                        input.inputType = InputType.TYPE_CLASS_TEXT
                        input.inputType = InputType.TYPE_CLASS_TEXT
                        builder.setView(input)

                        builder.setPositiveButton(
                                "OK",
                                DialogInterface.OnClickListener { dialog, which ->

                                    CoroutineScope(Dispatchers.IO).launch {
                                        runCatching {
                                            user.mdp= input.text.toString()
                                            val res = ServiceClient.instance.editUser(args.userId,user)
                                        }
                                    }
                                    Toast.makeText(MyApp.context,  user.identity_user(), Toast.LENGTH_SHORT).show()
                                    startActivity(ListClientActivity.getStartIntent(this.context))
                                })
                        builder.setNegativeButton(
                                "Retour",
                                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

                        builder.show()
                    }
                }
            }
        }
    }


    companion object {
        fun newInstance() = DetailsFragment()
    }
}