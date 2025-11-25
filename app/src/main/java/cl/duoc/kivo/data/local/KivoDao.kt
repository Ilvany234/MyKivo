package cl.duoc.kivo.data.local

import androidx.room.*
import cl.duoc.kivo.data.local.entities.UserEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.FavoriteEntity

@Dao
interface KivoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert suspend fun insertReview(review: ReviewEntity)
    @Query("SELECT * FROM reviews ORDER BY timestamp DESC")
    suspend fun getAllReviews(): List<ReviewEntity>

    @Insert suspend fun insertFavorite(fav: FavoriteEntity)
    @Query("SELECT * FROM favorites") suspend fun getAllFavorites(): List<FavoriteEntity>
}
