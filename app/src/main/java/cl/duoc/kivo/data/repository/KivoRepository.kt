package cl.duoc.kivo.data.repository

import cl.duoc.kivo.data.local.KivoDao
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.remote.ApiService
import cl.duoc.kivo.data.remote.dto.LoginRequest
import cl.duoc.kivo.data.remote.dto.ReviewRequest
import cl.duoc.kivo.data.remote.dto.SignupRequest
import cl.duoc.kivo.data.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KivoRepository @Inject constructor(
    private val api: ApiService,
    private val dao: KivoDao,
    private val sessionManager: SessionManager
) {

    // --- AUTENTICACIÓN (REMOTO) ---
    suspend fun register(name: String, email: String, password: String): Result<Unit> {
        return try {
            val request = SignupRequest(name = name, email = email, password = password)
            api.signup(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val request = LoginRequest(email = email, password = password)
            val response = api.login(request)
            sessionManager.saveSession(response.authToken, email, response.userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout() {
        sessionManager.clearSession()
    }

    // --- RESEÑAS (REMOTO) ---
    suspend fun getAllReviews(): Result<List<ReviewEntity>> {
        return try {
            val remoteReviews = api.getReviews()
            val mappedReviews = remoteReviews.map { ReviewEntity(id = it.id.toLong(), author = it.user.name, text = it.reviewText) }
            Result.success(mappedReviews)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun insertReview(text: String): Result<Unit> {
        return try {
            // --- ¡SOLUCIÓN! Solo enviamos el texto. Xano se encarga del autor.
            val request = ReviewRequest(reviewText = text)
            api.postReview(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // --- FAVORITOS (LOCAL) ---
    suspend fun getAllFavorites(): List<FavoriteEntity> {
        return dao.getAllFavorites()
    }

    suspend fun insertFavorite(word: String, description: String) {
        val favorite = FavoriteEntity(word = word, description = description)
        dao.insertFavorite(favorite)
    }
}
