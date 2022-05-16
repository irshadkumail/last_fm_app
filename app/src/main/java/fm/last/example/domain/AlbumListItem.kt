package fm.last.example.domain

import fm.last.example.domain.dto.AlbumListResponse
import fm.last.example.domain.dto.AlbumListResponseItem

data class AlbumListItem(
    val id: String,
    val name: String,
    val artistName: String,
    val image: String
) {
    companion object {

        private fun fromList(item: AlbumListResponseItem): AlbumListItem? {
            return if (item.mbid != null) AlbumListItem(
                id = item.mbid!!,
                name = item.name ?: "",
                artistName = item.artist ?: "",
                image = item.image?.find { it.text != null }?.text ?: "",
            ) else null
        }

        fun from(response: AlbumListResponse): List<AlbumListItem> {
            return if (response.results != null) {
                val results = response.results
                val albumList: List<AlbumListResponseItem> =
                    results.albummatches?.albums ?: emptyList()
                return albumList.mapNotNull { fromList(it) }
            } else {
                emptyList()
            }
        }

    }

}