package com.bjc.vodsampleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bjc.vodsampleapp.data.VodItem
import com.bjc.vodsampleapp.databinding.CardVodItemBinding
import com.bjc.vodsampleapp.network.ApiNetworkClient

class CardAdapter : ListAdapter<VodItem, RecyclerView.ViewHolder>(VodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VodViewHolder(CardVodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vodItem = getItem(position)
        (holder as VodViewHolder).bind(vodItem)
    }

    class VodViewHolder(private val binding: CardVodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: VodItem) {
            binding.apply {
                vodItem = item
                client = ApiNetworkClient()
                executePendingBindings()
            }
        }
    }
}

private class VodDiffCallback : DiffUtil.ItemCallback<VodItem>() {
    override fun areItemsTheSame(oldItem: VodItem, newItem: VodItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VodItem, newItem: VodItem): Boolean {
        return oldItem == newItem
    }

}