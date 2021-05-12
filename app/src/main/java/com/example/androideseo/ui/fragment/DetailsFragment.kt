package com.example.androideseo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.androideseo.R
import com.example.androideseo.data.LocalPreferences
import com.example.androideseo.databinding.ActivityConnexionBinding
import com.example.androideseo.databinding.ActivitySensorBinding
import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.app.MainActivity


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    private lateinit var binding: ActivityDetailBinding// <-- Référence à notre ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySensorBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

                    binding.testdelete?.setOnClickListener {
                        val user_name = user_name.text;
                        val password = password.text;
                        CoroutineScope(Dispatchers.IO).launch {
                            runCatching {
                                val res = ServiceClient.instance.connexion(user_name,password)
                                LocalPreferences.getInstance(this@ConnexionActivity).addTokenToHistory(res.token)
                                runOnUiThread{
                                    Toast.makeText(this@ConnexionActivity, "Vous etes connecté",
                                            Toast.LENGTH_SHORT).show()
                                    startActivity(MainActivity.getStartIntent(this@ConnexionActivity))
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    companion object {
        fun newInstance() = DetailsFragment()
    }
}