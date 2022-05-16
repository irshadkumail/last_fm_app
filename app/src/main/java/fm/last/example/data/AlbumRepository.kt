package fm.last.example.data

import fm.last.example.base.ApiModule
import fm.last.example.domain.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


class AlbumRepository @Inject constructor(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao,
    @Named(ApiModule.API_KEY) private val apiKey: String
) {

    suspend fun getAlbums(query: String, page: Int = 1) = withContext(Dispatchers.IO) {
        albumService.getAlbums(
            album = query,
            api_key = apiKey,
            page = page.toString()
        )
    }

    fun getAlbumInfoStream(id: String): Flow<Album?> = albumDao.findById(id)

    fun getLatestAlbumInfo(coroutineScope: CoroutineScope, id: String) {
        coroutineScope.launch(Dispatchers.IO) {
            val response = albumService.getAlbumInfo(
                id = id,
                api_key = apiKey
            )
            if (response.isSuccessful && response.body() != null) {
                val album = Album.from(id, response.body()!!)
                if (album != null) albumDao.insert(album)
            }
        }
    }

}