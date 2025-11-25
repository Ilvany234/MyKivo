package cl.duoc.kivo.features.auth.data

import cl.duoc.kivo.features.auth.domain.User
import kotlinx.coroutines.delay

/**
 * Repositorio responsable de autenticar usuarios.
 * Más adelante lo conectaremos a Room y Retrofit.
 */
class AuthRepository {

    private val fakeUsers = mutableListOf<User>()

    /**
     * Simula registro de usuario (luego se guardará en base de datos).
     */
    suspend fun register(
        nombre: String,
        correo: String,
        contraseña: String,
        edad: Int
    ): Result<User> {
        delay(800)

        val user = User(
            id = fakeUsers.size + 1,
            nombre = nombre,
            correo = correo,
            edad = edad
        )

        fakeUsers.add(user)

        return Result.success(user)
    }

    /**
     * Simula login verificando si el correo existe.
     */
    suspend fun login(correo: String, contraseña: String): Result<User> {
        delay(800)

        val encontrado = fakeUsers.find { it.correo == correo }

        return if (encontrado != null) {
            Result.success(encontrado)
        } else {
            Result.failure(Exception("Usuario no encontrado"))
        }
    }
}
