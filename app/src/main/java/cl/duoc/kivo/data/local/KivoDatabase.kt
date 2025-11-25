package cl.duoc.kivo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.duoc.kivo.data.local.entities.UserEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.FavoriteEntity

@Database(entities = [UserEntity::class, ReviewEntity::class, FavoriteEntity::class], version = 1)
abstract class KivoDatabase : RoomDatabase() {
    abstract fun kivoDao(): KivoDao
}
