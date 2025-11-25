package cl.duoc.kivo.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.features.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel que conecta la UI con el repositorio.
 * Maneja Login y Registro.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(correo: String, contraseña: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)

            val resultado = repository.login(correo, contraseña)

            _uiState.value = if (resultado.isSuccess) {
                AuthUiState(user = resultado.getOrNull())
            } else {
                AuthUiState(error = resultado.exceptionOrNull()?.message)
            }
        }
    }

    fun register(nombre: String, correo: String, contraseña: String, edad: Int) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)

            val resultado = repository.register(nombre, correo, contraseña, edad)

            _uiState.value = if (resultado.isSuccess) {
                AuthUiState(user = resultado.getOrNull())
            } else {
                AuthUiState(error = resultado.exceptionOrNull()?.message)
            }
        }
    }
}