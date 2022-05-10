package com.test.leboncointest.ui.albumlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.leboncointest.R
import com.test.leboncointest.databinding.AlbumListBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.album_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AlbumAdapter(lifecycleOwner = this)
        binding.recyclerView.adapter = adapter

        viewModel.albums.observe(viewLifecycleOwner) { imageBankItems ->
            adapter.setData(imageBankItems)
        }
    }
}