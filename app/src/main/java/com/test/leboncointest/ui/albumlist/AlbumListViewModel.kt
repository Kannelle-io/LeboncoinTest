package com.test.leboncointest.ui.albumlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.leboncointest.data.api.domain.AlbumRepository
import com.test.leboncointest.data.models.Album
import com.test.leboncointest.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class AlbumListViewModel(private val albumRepository: AlbumRepository) : BaseViewModel() {

    val canShowRecyclerView: LiveData<Boolean>
        get() = _canShowRecyclerView
    private val _canShowRecyclerView: MutableLiveData<Boolean> = MutableLiveData()

    val albums: LiveData<List<Album>>
        get() = _albums
    private val _albums: MutableLiveData<List<Album>> = MutableLiveData()

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch {
            albumRepository.fetchAlbums()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _isLoading.value = true
                            _hasError.value = false
                            _canShowRecyclerView.value = false
                        }
                        is Resource.Success -> {
                            _isLoading.value = false
                            _hasError.value = false
                            _canShowRecyclerView.value = true
                            _albums.value = result.data ?: emptyList()
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _hasError.value = true
                            _canShowRecyclerView.value = false
                        }
                    }

                }
        }
    }
}