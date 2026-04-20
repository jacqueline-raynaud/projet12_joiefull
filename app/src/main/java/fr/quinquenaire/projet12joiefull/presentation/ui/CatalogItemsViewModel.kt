package fr.quinquenaire.projet12joiefull.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.quinquenaire.projet12joiefull.domain.usecases.EnsureDataAvailable
import fr.quinquenaire.projet12joiefull.domain.usecases.GetCatalogItemsById
import fr.quinquenaire.projet12joiefull.domain.usecases.GetCatalogItemsList
import fr.quinquenaire.projet12joiefull.domain.usecases.ToggleFavorite
import fr.quinquenaire.projet12joiefull.domain.usecases.UpdateRating
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogItemsViewModel @Inject constructor(
    private val getCatalogItemsList: GetCatalogItemsList,
    private val updateRating: UpdateRating,
    private val toggleFavorite: ToggleFavorite,
    private val getCatalogItemsById: GetCatalogItemsById,
    private val ensureDataAvailable: EnsureDataAvailable
) : ViewModel() {

    private val _localState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = combine(
        getCatalogItemsList(),
        _localState
    ) { catalogItems, localState ->
        localState.copy(
            catalogItemsByCategory = catalogItems.groupBy { it.category }
        )
    }.stateIn(
        scope = viewModelScope,
        started=SharingStarted.WhileSubscribed(5000),
        initialValue = UiState(isLoading = true)
    )

    init {
        loadInitialData()
    }

    fun onSelectItem(id: Long) {
        getCatalogItemsById(id)
            .onEach { item ->
                _localState.update { it.copy(selectedItem = item) }
            }
            .catch { e ->
                _localState.update { it.copy(error = e.message ?: "Unknown error") }
            }
            .launchIn(viewModelScope)
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _localState.update { it.copy(isLoading = true) }
            try {
                ensureDataAvailable()
            } catch (e: Exception) {
                _localState.update { it.copy(error = e.message ?: "Unknown error") }
            } finally {
                _localState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onClearSelection() {
        _localState.update { it.copy(selectedItem = null) }
    }

    fun onToggleFavorite(id: Long) {
        viewModelScope.launch {
            try {
                toggleFavorite(id)
            } catch (e: Exception) {
                _localState.update { it.copy(error = e.message ?: "Unknown error") }
            }
        }
    }

    fun onUpdateRating(id: Long, rating: Float) {
        viewModelScope.launch {
            try {
                updateRating(id, rating)
            } catch (e: Exception) {
                _localState.update { it.copy(error = e.message ?: "Unknown error") }
            }
        }
    }
}
