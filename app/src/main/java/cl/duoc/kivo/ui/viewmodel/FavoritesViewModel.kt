package cl.duoc.kivo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.repository.KivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repo: KivoRepository) : ViewModel() {
    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteEntity>> = _favorites

    fun load() { viewModelScope.launch { _favorites.value = repo.getAllFavoritesLocal() } }
    fun add(word: String, desc: String) { viewModelScope.launch { repo.insertFavoriteLocal(FavoriteEntity(word = word, description = desc)); load() } }
}
