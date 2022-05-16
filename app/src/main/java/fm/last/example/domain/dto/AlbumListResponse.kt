package fm.last.example.domain.dto

import com.google.gson.annotations.SerializedName

data class AlbumListResponse(
    @SerializedName("results")
    val results: AlbumListResult? = null
)

data class AlbumListResult(
    @SerializedName("opensearch:Query")
    var query: Query? = null,
    @SerializedName("opensearch:totalResults")
    var totalResults: String? = null,
    @SerializedName("opensearch:startIndex")
    var startIndex: String? = null,
    @SerializedName("opensearch:itemsPerPage")
    var itemsPerPage: String? = null,
    @SerializedName("albummatches")
    var albummatches: AlbumMatches? = null,
)

data class AlbumMatches(
    @SerializedName("album") var albums: List<AlbumListResponseItem>? = null
)

data class Query(
    @SerializedName("#text") var text: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("searchTerms") var searchTerms: String? = null,
    @SerializedName("startPage") var startPage: String? = null

)

data class AlbumListResponseItem(
    @SerializedName("name") var name: String? = null,
    @SerializedName("artist") var artist: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("image") var image: List<Image>? = null,
    @SerializedName("streamable") var streamable: String? = null,
    @SerializedName("mbid") var mbid: String? = null
)