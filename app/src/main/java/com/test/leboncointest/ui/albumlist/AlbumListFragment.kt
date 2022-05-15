package com.test.leboncointest.ui.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.leboncointest.AlbumActivity
import com.test.leboncointest.R
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.databinding.AlbumListBinding
import com.test.leboncointest.utils.BundleKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    private lateinit var adapter: AlbumAdapter
    private lateinit var binding: AlbumListBinding
    private val viewModel: AlbumListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.album_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onItemClickListener = object : AlbumAdapter.OnItemClickListener {
            override fun onItemClick(album: Album) {
                onAlbumClick(album)
            }
        }

        adapter = AlbumAdapter(lifecycleOwner = this, onItemClickListener = onItemClickListener)
        binding.recyclerView.adapter = adapter

        viewModel.albums.observe(viewLifecycleOwner) { imageBankItems ->
            adapter.setData(imageBankItems)
        }

    }

    fun onAlbumClick(album: Album) {
        val navController = findNavController()
        val bundle = bundleOf(BundleKeys.album to album)
        navController.navigate(R.id.action_albumListFragment_to_albumDetailFragment, bundle)
    }
}