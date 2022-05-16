package fm.last.example.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fm.last.example.data.AlbumDao
import fm.last.example.domain.Album

@Database(
    entities = [Album::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}
