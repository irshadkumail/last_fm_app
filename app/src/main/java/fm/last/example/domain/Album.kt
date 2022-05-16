package fm.last.example.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fm.last.example.domain.dto.AlbumInfoResponse


@Entity(tableName = "album_info")
data class Album(
    @PrimaryKey()
    var id: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "artist") var artist: String = "",
    @ColumnInfo(name = "image") var imageUrl: String = "",
    @ColumnInfo(name = "published") var published: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "listeners") var listeners: String = "",
    @ColumnInfo(name = "playcount") var playcount: String = ""
) {
    companion object {

        fun from(id: String, info: AlbumInfoResponse): Album? {
            val albumResponse = info.album
            if (albumResponse != null) {
                return Album(
                    id = id,
                    title = albumResponse.name ?: "",
                    artist = albumResponse.artist ?: "",
                    imageUrl = albumResponse.image?.find { it.text != null && it.size == "large" }?.text
                        ?: "",
                    published = albumResponse.wiki?.published ?: "",
                    content = albumResponse.wiki?.content ?: "",
                    listeners = albumResponse.listeners ?: "",
                    playcount = albumResponse.playcount ?: ""
                )
            } else {
                return null
            }
        }
    }

}
