package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {

    private val _reviews = MutableStateFlow<List<ReviewEntity>>(emptyList())
    val reviews: StateFlow<List<ReviewEntity>> = _reviews.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadReviews()
    }

    private fun loadReviews() {
        viewModelScope.launch {
            repo.getAllReviews()
                .onSuccess { _reviews.value = it }
                .onFailure { _error.value = "Error al cargar las reseñas" }
        }
    }

    fun addReview(text: String) {
        viewModelScope.launch {
            if (text.isNotBlank()) {
                repo.insertReview(text)
                    .onSuccess { loadReviews() } // Recargamos si la inserción es exitosa
                    .onFailure { _error.value = "Error al publicar la reseña" }
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
