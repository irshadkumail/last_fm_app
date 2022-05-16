package fm.last.example.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fm.last.example.data.AlbumRepository
import fm.last.example.domain.Album
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val repository: AlbumRepository) :
    ViewModel() {

    val albumInfo = mutableStateOf(Album())

    fun init(id: String) {
        repository.getLatestAlbumInfo(viewModelScope, id)
        viewModelScope.launch {
            try {
                repository.getAlbumInfoStream(id).collect {
                    if (it != null) albumInfo.value = it
                }
            } catch (ex: Exception) {
                Log.d("Irshad", ex.message.toString())
            }
        }
    }

}