package fm.last.example.data

import androidx.room.*
import fm.last.example.domain.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album_info")
    fun getAll(): Flow<List<Album>>

    @Query("SELECT * FROM album_info WHERE id = :id")
    fun findById(id: String): Flow<Album?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: Album)

    @Delete
    fun delete(album: Album)

}