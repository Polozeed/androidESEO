package com.example.androideseo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.androideseo.R
import com.example.androideseo.data.models.Information
import com.example.androideseo.service.ServiceInformation
import com.example.androideseo.ui.fragment.viewHolder.LocalInfoViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * TODO
 * Classe permettant affichage liste info
 */
class ListInfoFragment : Fragment() {

    val dataSource = emptyDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_infolist, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.findViewById<Button>(R.id.afficheHistorique).setOnClickListener { getData() }

        view.findViewById<RecyclerView>(R.id.info).setup {
            withDataSource(dataSource)
            withLayoutManager(LinearLayoutManager(this@ListInfoFragment.requireContext()))
            withItem<Information, LocalInfoViewHolder>(R.layout.item_infolist) {
                onBind(::LocalInfoViewHolder) { _, item ->
                    title.text = "${item.id_info} ${item.luminosite} ${item.proximite} ${item.gravite} ${item.acceleration}"
                }
                onClick {
                    findNavController().navigate(ListFragmentDirections.actionFirstFragmentToSecondFragment(item.id_info.toInt()))
                }
            }
        }

    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = ServiceInformation.instance.afficheInfos()
            activity?.runOnUiThread {
                dataSource.addAll(users)
            }
        }
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}