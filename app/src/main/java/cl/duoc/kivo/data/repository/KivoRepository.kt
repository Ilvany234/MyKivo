package cl.duoc.kivo.data.repository

import cl.duoc.kivo.data.User
import cl.duoc.kivo.data.local.KivoDao
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.UserEntity
import cl.duoc.kivo.data.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KivoRepository @Inject constructor(
    private val dao: KivoDao,
    private val sessionManager: SessionManager
) {

    // --- LÓGICA DE AUTENTICACIÓN ---
    suspend fun register(name: String, email: String, password: String, age: Int): Result<User> {
        delay(500)
        if (dao.getUserByEmail(email) != null) {
            return Result.failure(Exception("El correo ya está en uso"))
        }
        val userEntity = UserEntity(name = name, email = email, age = age, password = password)
        dao.insertUser(userEntity)
        val user = User(name = name, email = email, age = age, password = password)
        return Result.success(user)
    }

    suspend fun login(email: String, password: String): Result<User> {
        delay(500)
        var userEntity = dao.getUserByEmail(email)

        if (userEntity == null && email == "test@kivo.com") {
            val testUser = UserEntity(name = "Usuario de prueba", email = "test@kivo.com", age = 20, password = "123")
            dao.insertUser(testUser)
            userEntity = testUser
        }

        return when {
            userEntity == null -> Result.failure(Exception("Usuario no encontrado"))
            userEntity.password != password -> Result.failure(Exception("Contraseña incorrecta"))
            else -> {
                val user = User(name = userEntity.name, email = userEntity.email, age = userEntity.age, password = userEntity.password)
                sessionManager.saveSession(token = "fake_jwt_token_for_session", email = user.email)
                Result.success(user)
            }
        }
    }

    suspend fun logout() {
        sessionManager.clearSession()
    }

    // --- LÓGICA DE RESEÑAS ---
    suspend fun getAllReviews(): List<ReviewEntity> {
        return dao.getAllReviews()
    }

    suspend fun insertReview(text: String) {
        val userEmail = sessionManager.emailFlow.first()
        if (userEmail == null) return
        val user = dao.getUserByEmail(userEmail)
        if (user == null) return
        val review = ReviewEntity(author = user.name, text = text)
        dao.insertReview(review)
    }
    
    // --- LÓGICA DE FAVORITOS ---
    suspend fun getAllFavorites(): List<FavoriteEntity> {
        return dao.getAllFavorites()
    }

    suspend fun insertFavorite(word: String, description: String) {
        val favorite = FavoriteEntity(word = word, description = description)
        dao.insertFavorite(favorite)
    }
}
