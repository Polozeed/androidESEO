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
import com.example.androideseo.data.models.LocalUser
import com.example.androideseo.service.ServiceClient
import com.example.androideseo.ui.app.PhotoActivity


import com.example.androideseo.ui.fragment.viewHolder.LocalUserViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    val dataSource = emptyDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.findViewById<Button>(R.id.refresh).setOnClickListener { getData() }

        view.findViewById<RecyclerView>(R.id.rvUser).setup {
            withDataSource(dataSource)
            withLayoutManager(LinearLayoutManager(this@ListFragment.requireContext()))
            withItem<LocalUser, LocalUserViewHolder>(R.layout.item_list) {
                onBind(::LocalUserViewHolder) { _, item ->
                    title.text = "${item.login} ${item.mdp}"

                }
                onClick {
                    findNavController().navigate(ListFragmentDirections.actionFirstFragmentToSecondFragment(item.id_client))
                }
            }
        }
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = ServiceClient.instance.getUsers()
            activity?.runOnUiThread {
                dataSource.addAll(users)
            }
        }
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}