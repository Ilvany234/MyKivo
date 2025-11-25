package cl.duoc.kivo.data

import kotlinx.coroutines.delay

/**
 * AuthRepository
 * ----------------------------
 * Encargado de manejar login y registro.
 * Ahora simula un servidor real.
 * Más adelante conectaremos:
 *   - API REST (Retrofit)
 *   - Room (BD interna)
 */
class AuthRepository {

    // Simulación de registro
    suspend fun register(
        name: String,
        email: String,
        password: String,
        age: Int
    ): Result<User> {

        delay(1000) // simula red

        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            return Result.failure(Exception("Todos los campos son obligatorios"))
        }

        val user = User(name = name, email = email, age = age)
        return Result.success(user)
    }

    // Simulación de login
    suspend fun login(email: String, password: String): Result<User> {

        delay(1000) // simula red

        if (email == "test@kivo.com" && password == "123") {
            return Result.success(User("Usuario de prueba", email, 20))
        }

        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Ingrese correo y contraseña"))
        }

        return Result.failure(Exception("Credenciales incorrectas"))
    }
}
