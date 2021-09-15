package com.bjc.vodsampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bjc.vodsampleapp.adapters.CardAdapter
import com.bjc.vodsampleapp.databinding.FragmentVodListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VodListFragment : Fragment() {

    companion object {
        fun newInstance() = VodListFragment()
    }

    private val viewModel: VodListViewModel by viewModels()
//    private val client: ApiNetworkClient = ApiNetworkClient()
//    private val client: Pac12DataClient = Pac12DataClient.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVodListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = CardAdapter()
        binding.vodList.adapter = adapter
        subscribeToObserver(adapter)
        return binding.root
    }

    private fun subscribeToObserver(adapter: CardAdapter) {
//        val vodList = client.getVodList()
//        val vodList = client.getVodList()
        lifecycleScope.launch {
            viewModel.vodItems.collectLatest { adapter.submitData(it) }
        }
//        adapter.submitList(vodList)
    }

}