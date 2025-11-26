package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {
    private val _reviews = MutableStateFlow<List<ReviewEntity>>(emptyList())
    val reviews: StateFlow<List<ReviewEntity>> = _reviews

    fun load() { viewModelScope.launch { _reviews.value = repo.getAllReviewsLocal() } }
    fun add(authorEmail: String, text: String) { viewModelScope.launch { repo.insertReviewLocal(ReviewEntity(authorEmail = authorEmail, text = text)); load() } }
}
