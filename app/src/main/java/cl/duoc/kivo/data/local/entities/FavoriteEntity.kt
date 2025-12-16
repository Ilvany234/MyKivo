package cl.duoc.kivo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    // --- ¡SOLUCIÓN! Se añade la clave primaria que faltaba ---
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val word: String,
    val description: String,
    val userEmail: String   // ← ¡Nuevo!
)

