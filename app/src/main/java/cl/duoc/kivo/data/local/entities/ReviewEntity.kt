package cl.duoc.kivo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val author: String, // Guardaremos el nombre del autor directamente
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
