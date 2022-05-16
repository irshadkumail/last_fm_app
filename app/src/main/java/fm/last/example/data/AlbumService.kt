package fm.last.example.data

import fm.last.example.domain.dto.AlbumInfoResponse
import fm.last.example.domain.dto.AlbumListResponse
import fm.last.example.domain.dto.AlbumListResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AlbumService {

    @GET("2.0/")
    suspend fun getAlbums(
        @Query("method") method: String = "album.search",
        @Query("album") album: String,
        @Query("api_key") api_key: String,
        @Query("page") page: String,
        @Query("format") format: String = "json"
    ): Response<AlbumListResponse>


    @GET("2.0/")
    suspend fun getAlbumInfo(
        @Query("method") method: String = "album.getinfo",
        @Query("mbid") id: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String = "json"
    ): Response<AlbumInfoResponse>
}