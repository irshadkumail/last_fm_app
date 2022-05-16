package fm.last.example.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fm.last.example.data.AlbumRepository
import fm.last.example.domain.AlbumListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val repository: AlbumRepository) :
    ViewModel() {

    val isLoading = mutableStateOf(false)
    val albums = mutableStateListOf<AlbumListItem>()
    val searchText = mutableStateOf("")
    var page = 1

    fun onSearchQueryEntered(searchText: String?) {
        if (searchText == null)
            return
        this.page = 1
        this.searchText.value = searchText
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getAlbums(searchText)
            isLoading.value = false
            if (response.isSuccessful && response.body() != null) {
                albums.clear()
                albums.addAll(AlbumListItem.from(response.body()!!))
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            val response = repository.getAlbums(searchText.value, ++page)
            if (response.isSuccessful && response.body() != null) {
                albums.addAll(AlbumListItem.from(response.body()!!))
            }
        }
    }

}