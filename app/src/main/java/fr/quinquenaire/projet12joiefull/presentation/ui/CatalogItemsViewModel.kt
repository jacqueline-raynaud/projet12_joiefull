package fr.quinquenaire.projet12joiefull.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.quinquenaire.projet12joiefull.domain.usecases.CommentItem
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the UI state and user interactions for the catalog items screen.
 * It make synchronization between the local Json and the Room database at startup
 * and exposes a reactive state via [catalogUiState]
 */
@HiltViewModel
class CatalogItemsViewModel @Inject constructor(
    private val getCatalogItemsList: GetCatalogItemsList,
    private val updateRating: UpdateRating,
    private val toggleFavorite: ToggleFavorite,
    private val getCatalogItemsById: GetCatalogItemsById,
    private val ensureDataAvailable: EnsureDataAvailable,
    private val commentItem: CommentItem
) : ViewModel() {

    /**Combine and map
     * combines database data with local state
     * to create a unified state for the UI [catalogUiState]
     * and map to transforme simple list into an organized structure
     * by categories for display in section in interface [CategorySection]
    **/
    private val _localState = MutableStateFlow(CatalogUiState())

    val catalogUiState: StateFlow<CatalogUiState> = combine(
        getCatalogItemsList(),
        _localState
    ) { catalogItems, localState ->
        localState.copy(
            categories = catalogItems.groupBy { it.category }
                .map { (name, items) -> CategorySection(name, items) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CatalogUiState(isLoading = true)
    )

    init {
        loadInitialData()
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

    fun onCommentItem(id: Long, comment: String) {
        viewModelScope.launch {
            try {
                commentItem(id, comment)
            } catch (e: Exception) {
                _localState.update { it.copy(error = e.message ?: "Unknown error") }
            }
        }
    }
}
