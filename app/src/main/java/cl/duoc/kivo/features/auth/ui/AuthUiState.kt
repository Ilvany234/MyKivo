package cl.duoc.kivo.features.auth.ui

import cl.duoc.kivo.features.auth.domain.User

/**
 * Representa estados de la pantalla:
 * - Cargando
 * - Error
 * - Usuario logeado
 */
data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)
