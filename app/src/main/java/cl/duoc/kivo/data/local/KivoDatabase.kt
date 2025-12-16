package cl.duoc.kivo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.UserEntity

// --- ¡SOLUCIÓN! Se incrementa la versión de la base de datos ---
@Database(entities = [UserEntity::class, ReviewEntity::class, FavoriteEntity::class], version = 2, exportSchema = false)
abstract class KivoDatabase : RoomDatabase() {
    abstract fun kivoDao(): KivoDao
}
