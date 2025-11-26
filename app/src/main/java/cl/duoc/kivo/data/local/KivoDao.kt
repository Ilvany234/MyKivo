package cl.duoc.kivo.data.local

import androidx.room.*
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.UserEntity

@Dao
interface KivoDao {
    // Users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getAnyUser(): UserEntity?

    // Reviews
    @Insert
    suspend fun insertReview(review: ReviewEntity)

    @Query("SELECT * FROM reviews ORDER BY timestamp DESC")
    suspend fun getAllReviews(): List<ReviewEntity>

    // Favorites
    @Insert
    suspend fun insertFavorite(fav: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEntity>
}
