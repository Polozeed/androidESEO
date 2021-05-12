package com.example.androideseo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androideseo.R
import com.example.androideseo.service.ServiceInformation
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
        // Inflate the layout for this fragment
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

                    identity.text = info.identity()


                }

            }
        }
    }


    companion object {
        fun newInstance() = DetailsFragment()
    }
}