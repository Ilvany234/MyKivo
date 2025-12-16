package cl.duoc.kivo.data.local

import androidx.room.*
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.UserEntity

@Dao
interface KivoDao {
    // --- USUARIOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    // --- RESEÑAS ---
    @Insert
    suspend fun insertReview(review: ReviewEntity)

    @Query("SELECT * FROM reviews ORDER BY timestamp DESC")
    suspend fun getAllReviews(): List<ReviewEntity>

    // --- FAVORITOS ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(fav: FavoriteEntity)

    // --- ¡SOLUCIÓN! Se añade el filtro por email del usuario ---
    @Query("SELECT * FROM favorites WHERE userEmail = :userEmail")
    suspend fun getAllFavorites(userEmail: String): List<FavoriteEntity>
}
