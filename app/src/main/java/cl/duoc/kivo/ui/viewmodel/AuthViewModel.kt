package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel de autenticación.
 * Maneja:
 * - login
 * - registro
 * - estados (loading, success, error)
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading

            val result = repository.login(email, password)

            _state.value = result.fold(
                onSuccess = { AuthState.Success("Inicio de sesión correcto") },
                onFailure = { AuthState.Error(it.message ?: "Error desconocido") }
            )
        }
    }

    fun register(name: String, email: String, password: String, age: Int) {
        viewModelScope.launch {
            _state.value = AuthState.Loading

            val result = repository.register(name, email, password, age)

            _state.value = result.fold(
                onSuccess = { AuthState.Success("Registro exitoso") },
                onFailure = { AuthState.Error(it.message ?: "Error desconocido") }
            )
        }
    }

    fun resetState() {
        _state.value = AuthState.Idle
    }
}
