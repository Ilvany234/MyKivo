package cl.duoc.kivo.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
=======
import cl.duoc.kivo.data.User
>>>>>>> 868d0746111ac68b45e7cb79d6ceac74d756bfd6
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
<<<<<<< HEAD
class AuthViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {
=======
class AuthViewModel @Inject constructor(
    private val repo: KivoRepository
) : ViewModel() {
>>>>>>> 868d0746111ac68b45e7cb79d6ceac74d756bfd6

    data class CampoEstado(val valor: String = "", val tocado: Boolean = false, val error: String = "")

    var nombre = mutableStateOf(CampoEstado())
    var correo = mutableStateOf(CampoEstado())
    var clave = mutableStateOf(CampoEstado())
    var terminos = mutableStateOf(false)

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    // ⬅️ Nuevo: Para mostrar los datos del usuario en ProfileScreen
    var currentUser = mutableStateOf<User?>(null)

    fun loadUserFromSession() {
        viewModelScope.launch {
            val user = repo.getLoggedUser()
            currentUser.value = user
        }
    }

    fun onNombreChange(valor: String) {
        nombre.value = nombre.value.copy(valor = valor, tocado = true)
        validarNombre()
    }

    fun onCorreoChange(valor: String) {
        correo.value = correo.value.copy(valor = valor, tocado = true)
        validarCorreo()
    }

    fun onClaveChange(valor: String) {
        clave.value = clave.value.copy(valor = valor, tocado = true)
        validarClave()
    }

    fun onTerminosChange(valor: Boolean) {
        terminos.value = valor
    }

    private fun validarNombre() {
        val errorMsg = if (nombre.value.valor.length < 3) "Debe tener al menos 3 caracteres" else ""
        nombre.value = nombre.value.copy(error = if (nombre.value.tocado) errorMsg else "")
    }

    private fun validarCorreo() {
        val errorMsg = if (!correo.value.valor.contains("@")) "Correo inválido" else ""
        correo.value = correo.value.copy(error = if (correo.value.tocado) errorMsg else "")
    }

    // --- ¡SOLUCIÓN! Se añade validación de contraseña robusta ---
    private fun validarClave() {
        val clave = clave.value.valor
        val errorMsg = when {
            clave.length < 8 -> "Mínimo 8 caracteres"
            !clave.any { it.isUpperCase() } -> "Debe contener una mayúscula"
            !clave.any { it.isLowerCase() } -> "Debe contener una minúscula"
            !clave.any { it.isDigit() } -> "Debe contener un número"
            else -> ""
        }
        this.clave.value = this.clave.value.copy(error = if (this.clave.value.tocado) errorMsg else "")
    }

    private fun verificarRegistro(): Boolean {
        validarNombre()
        validarCorreo()
        validarClave()
        return nombre.value.error.isEmpty() &&
                correo.value.error.isEmpty() &&
                clave.value.error.isEmpty() &&
                terminos.value
    }

    fun register() {
        if (!verificarRegistro()) {
            _uiState.value = UiState.Error("Corrige los errores y acepta los términos")
            return
        }

        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = repo.register(
                name = nombre.value.valor,
                email = correo.value.valor,
                password = clave.value.valor
            )
            result.onSuccess {
                _uiState.value = UiState.Success("¡Registro exitoso!")
            }
            result.onFailure { error ->
                _uiState.value = UiState.Error(error.message ?: "Error desconocido")
            }
        }
    }

    fun login() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = repo.login(correo.value.valor, clave.value.valor)
            result.onSuccess { user ->
                currentUser.value = user
                _uiState.value = UiState.Success("¡Inicio de sesión exitoso!")
            }
            result.onFailure { error ->
                _uiState.value = UiState.Error(error.message ?: "Error desconocido")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
            currentUser.value = null
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}
