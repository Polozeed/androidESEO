package com.example.androideseo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
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
                    val identity = findViewById<TextView>(R.id.identity)
                    identity.text = user.identity()

                    findViewById<Button>(R.id.deleteclient)?.setOnClickListener {
                        CoroutineScope(Dispatchers.IO).launch {
                            runCatching {
                                val res = ServiceClient.instance.deleteUser(args.userId)
                            }
                        }
                        Toast.makeText(MyApp.context, "Element Supprimé",
                                Toast.LENGTH_SHORT).show()
                        startActivity(ListClientActivity.getStartIntent(this.context))

                    }

                    findViewById<Button>(R.id.modifierclient)?.setOnClickListener {
                        CoroutineScope(Dispatchers.IO).launch {
                            runCatching {
                                val res = ServiceClient.instance.editUser(args.userId)
                            }
                        }
                        Toast.makeText(MyApp.context, "Element Supprimé",
                                Toast.LENGTH_SHORT).show()
                        startActivity(ListClientActivity.getStartIntent(this.context))


                    }
                }
            }
                }
            }





    companion object {
        fun newInstance() = DetailsFragment()
    }
}