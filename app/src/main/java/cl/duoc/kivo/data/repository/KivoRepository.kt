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
    val sessionManager: SessionManager
) {

<<<<<<< HEAD
    // --- AUTENTICACIÓN (REMOTO) ---
    suspend fun register(name: String, email: String, password: String): Result<Unit> {
        return try {
            val request = SignupRequest(name = name, email = email, password = password)
            api.signup(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
=======
    // --- AUTH ---
    suspend fun register(name: String, email: String, password: String, age: Int): Result<User> {
        delay(500)
        if (dao.getUserByEmail(email) != null) {
            return Result.failure(Exception("El correo ya está en uso"))
>>>>>>> 868d0746111ac68b45e7cb79d6ceac74d756bfd6
        }
    }

<<<<<<< HEAD
    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val request = LoginRequest(email = email, password = password)
            val response = api.login(request)
            sessionManager.saveSession(response.authToken, email, response.userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
=======
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
                val user = User(
                    name = userEntity.name,
                    email = userEntity.email,
                    age = userEntity.age,
                    password = userEntity.password
                )
                sessionManager.saveSession(token = "fake_jwt_token", email = user.email)
                Result.success(user)
            }
>>>>>>> 868d0746111ac68b45e7cb79d6ceac74d756bfd6
        }
    }

    suspend fun logout() {
        sessionManager.clearSession()
    }

<<<<<<< HEAD
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
=======
    // --- PERFIL ---
    suspend fun getLoggedEmail(): String? {
        return sessionManager.emailFlow.first()
    }

    suspend fun getUserByEmail(email: String): UserEntity? {
        return dao.getUserByEmail(email)
    }

    // --- REVIEWS ---
    suspend fun getAllReviews(): List<ReviewEntity> = dao.getAllReviews()

    suspend fun insertReview(text: String) {
        val userEmail = sessionManager.emailFlow.first() ?: return
        val user = dao.getUserByEmail(userEmail) ?: return
        val review = ReviewEntity(author = user.name, text = text)
        dao.insertReview(review)
    }

    // --- FAVORITOS ---
>>>>>>> 868d0746111ac68b45e7cb79d6ceac74d756bfd6
    suspend fun getAllFavorites(): List<FavoriteEntity> {
        val email = sessionManager.emailFlow.first() ?: return emptyList()
        return dao.getFavoritesByUser(email)
    }

    suspend fun insertFavorite(word: String, description: String) {
        val email = sessionManager.emailFlow.first() ?: return

        val favorite = FavoriteEntity(
            word = word,
            description = description,
            userEmail = email
        )

        dao.insertFavorite(favorite)
    }


    suspend fun getLoggedUser(): User? {
        val email = sessionManager.emailFlow.first() ?: return null
        val user = dao.getUserByEmail(email) ?: return null
        return User(name = user.name, email = user.email, age = user.age, password = user.password)
    }

}

