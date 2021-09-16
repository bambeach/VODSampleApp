package com.bjc.vodsampleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bjc.vodsampleapp.R
import com.bjc.vodsampleapp.data.VodItem
import com.bjc.vodsampleapp.data.VodItemWithNames
import com.bjc.vodsampleapp.databinding.CardVodItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_vod_item.view.*

class CardAdapter() : PagingDataAdapter<VodItemWithNames, RecyclerView.ViewHolder>(VodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VodViewHolder(CardVodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vodItem = getItem(position)
        if (vodItem != null) {
            Picasso.get().load(vodItem.images.large).into((holder as VodViewHolder).vodImage)
            (holder as VodViewHolder).bind(vodItem)
        }
    }

    class VodViewHolder(private val binding: CardVodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val vodImage: ImageView = binding.cardImage

        fun bind(item: VodItemWithNames) {
            binding.apply {
                vodItem = item
                executePendingBindings()
            }
        }
    }
}

private class VodDiffCallback : DiffUtil.ItemCallback<VodItemWithNames>() {
    override fun areItemsTheSame(oldItem: VodItemWithNames, newItem: VodItemWithNames): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VodItemWithNames, newItem: VodItemWithNames): Boolean {
        return oldItem == newItem
    }

}