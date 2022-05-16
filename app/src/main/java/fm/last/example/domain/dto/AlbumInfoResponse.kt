package fm.last.example.domain.dto

import com.google.gson.annotations.SerializedName

data class AlbumInfoResponse(
    @SerializedName("album") var album: AlbumResult? = null,
)

data class AlbumResult(
    @SerializedName("artist") var artist: String? = null,
//    @SerializedName("tags") var tags: List<Tag>? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: List<Image>? = null,
  //  @SerializedName("tracks") var tracks: List<Track>? = null,
    @SerializedName("listeners") var listeners: String? = null,
    @SerializedName("playcount") var playcount: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("wiki") var wiki: Wiki? = Wiki()
)

data class Tag(
    @SerializedName("url") var url: String? = null,
    @SerializedName("name") var name: String? = null
)

data class Image(
    @SerializedName("size") var size: String? = null,
    @SerializedName("#text") var text: String? = null
)

data class Wiki(
    @SerializedName("published") var published: String? = null,
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("content") var content: String? = null
)

data class Track(
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("artist") var artist: Artist? = null
)

data class Artist(

    @SerializedName("url") var url: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("mbid") var mbid: String? = null

)