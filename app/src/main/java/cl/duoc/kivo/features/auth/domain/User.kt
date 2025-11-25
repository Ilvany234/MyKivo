package cl.duoc.kivo.features.auth.domain

/**
 * Modelo de usuario usado en la app (capa de dominio).
 * No es el mismo que el de la base de datos.
 */
data class User(
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val edad: Int,
)
