package com.test.leboncointest.ui.albumlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.test.leboncointest.R
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.databinding.AlbumItemBinding


internal class AlbumAdapter constructor(
    private val lifecycleOwner: LifecycleOwner,
    val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(), LifecycleOwner {

    private var albumItemViewModels: List<AlbumItemViewModel> = listOf()

    interface OnItemClickListener {
        fun onItemClick(album: Album)
    }

    fun setData(albums: List<Album>?) {
        this.albumItemViewModels = albums?.map {
            AlbumItemViewModel(it)
        } ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = DataBindingUtil.inflate<AlbumItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_album,
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albumItemViewModels.count()
    }

    private fun getAlbumItemViewModel(position: Int): AlbumItemViewModel {
        return albumItemViewModels[position]
    }

    private fun getAlbumItemViewModels(): List<AlbumItemViewModel> {
        return albumItemViewModels
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleOwner.lifecycle
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val viewModel = albumItemViewModels[position]
        holder.setViewModel(viewModel)
        holder.getBinding().lifecycleOwner = this

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(viewModel.album)
        }
    }

    internal class AlbumViewHolder constructor(private var binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setViewModel(viewModel: AlbumItemViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        fun getBinding(): AlbumItemBinding {
            return binding
        }

    }

}