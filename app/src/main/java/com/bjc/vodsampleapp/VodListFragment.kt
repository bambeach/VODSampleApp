package com.bjc.vodsampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bjc.vodsampleapp.adapters.CardAdapter
import com.bjc.vodsampleapp.databinding.FragmentVodListBinding
import com.bjc.vodsampleapp.network.ApiNetworkClient

class VodListFragment : Fragment() {

    companion object {
        fun newInstance() = VodListFragment()
    }

    private val viewModel: VodListViewModel by viewModels()
    private val client: ApiNetworkClient = ApiNetworkClient()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVodListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = CardAdapter()
        binding.vodList.adapter = adapter
        subscribeToObserver(adapter)
        return binding.root
    }

    private fun subscribeToObserver(adapter: CardAdapter) {
        val vodList = client.getVodList()
        adapter.submitList(vodList)
    }

}