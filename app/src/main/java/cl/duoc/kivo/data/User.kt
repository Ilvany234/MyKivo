package cl.duoc.kivo.data

/**
 * Modelo base de usuario.
 * Más adelante este modelo se integrará con:
 * - Room (Entity)
 * - Retrofit (Response)
 */
data class User(
    val name: String,
    val email: String,
    val age: Int
)