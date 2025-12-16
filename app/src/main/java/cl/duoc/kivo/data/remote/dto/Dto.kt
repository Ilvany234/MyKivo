package cl.duoc.kivo.data.remote.dto

import com.google.gson.annotations.SerializedName

// --- DTOs para Autenticación ---

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("authToken")
    val authToken: String,
    @SerializedName("id")
    val userId: Int
)


// --- DTOs para Reseñas ---

// --- ¡SOLUCIÓN! Solo enviamos el texto. El servidor se encargará del autor.

data class ReviewRequest(
    @SerializedName("review_text")
    val reviewText: String
)

// --- ¡SOLUCIÓN! Se define la respuesta para que incluya el objeto "user" del Addon
data class ReviewResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("review_text")
    val reviewText: String,
    @SerializedName("user") // Nombre del Addon que crearemos en Xano
    val user: UserDetails
)

// DTO para el objeto de usuario anidado dentro de la reseña
data class UserDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
