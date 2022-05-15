package com.test.leboncointest.ui.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
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


        val oldList = this.albumItemViewModels
        val newList = albums?.map {
            AlbumItemViewModel(it)
        } ?: emptyList()

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.count()
            }

            override fun getNewListSize(): Int {
                return newList.count()
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                val oldItemId = oldItem.album
                val newItemId = newItem.album
                return oldItemId == newItemId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem == newItem
            }
        })
        this.albumItemViewModels = newList
        diffResult.dispatchUpdatesTo(this)
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