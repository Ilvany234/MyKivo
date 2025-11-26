package cl.duoc.kivo.data

/**
 * Modelo base de usuario.
 * Contiene la contraseña para la simulación.
 * En un caso real, la contraseña NUNCA debería estar en el modelo de la UI.
 */
data class User(
    val name: String,
    val email: String,
    val age: Int,
    val password: String // ¡Solo para simulación!
)