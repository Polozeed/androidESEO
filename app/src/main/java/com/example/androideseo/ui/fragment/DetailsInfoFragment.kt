package com.example.androideseo.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.ServiceInfo
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
import com.example.androideseo.service.ServiceInformation
import com.example.androideseo.ui.app.MyApp
import com.example.androideseo.ui.fragment.client.ListClientActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsInfoFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infodetails, container, false)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val info = ServiceInformation.instance.afficheUneInfo(args.userId)

            activity?.runOnUiThread {
                view?.apply {
                    val identity = findViewById<TextView>(R.id.infoidentity)
                    identity.text = info.getIdString()
                    val identity2 = findViewById<TextView>(R.id.infoidentity2)
                    identity2.text = info.getProximiteString()
                    val identity3 = findViewById<TextView>(R.id.infoidentity3)
                    identity3.text = info.getLuminositetring()
                    val identity4 = findViewById<TextView>(R.id.infoidentity4)
                    identity4.text = info.getGraviteString()
                    val identity5 = findViewById<TextView>(R.id.infoidentity5)
                    identity5.text = info.getAccelerationString()

                    findViewById<Button>(R.id.deleteinfo)?.setOnClickListener {
                        CoroutineScope(Dispatchers.IO).launch {
                            runCatching {
                                val res = ServiceInformation.instance.deleteInfo(args.userId)
                            }
                        }
                        Toast.makeText(MyApp.context, "Element Supprimé", Toast.LENGTH_SHORT).show()
                        startActivity(HistoriqueInfoActivity.getStartIntent(this.context))

                    }
                }
            }
        }
    }


    companion object {
        fun newInstance() = DetailsFragment()
    }
}