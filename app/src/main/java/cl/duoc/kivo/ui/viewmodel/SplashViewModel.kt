package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {
    private val _hasUser = MutableStateFlow<Boolean?>(null)
    val hasUser: StateFlow<Boolean?> = _hasUser

    init {
        viewModelScope.launch {
            val u = repo.getAnyLocalUser()
            _hasUser.value = (u != null)
        }
    }
}
