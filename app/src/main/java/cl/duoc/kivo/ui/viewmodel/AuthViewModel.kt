package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.local.entities.UserEntity
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {
    fun register(name: String, email: String, password: String, age: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                repo.registerLocalUser(UserEntity(email = email, name = name, password = password, age = age))
                onSuccess()
            } catch (e: Exception) { onError(e.message ?: "Error") }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val user = repo.getLocalUser(email)
            if (user != null && user.password == password) onSuccess() else onError("Credenciales inválidas")
        }
    }
}
