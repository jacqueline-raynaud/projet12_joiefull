package fr.quinquenaire.projet12joiefull.presentation.ui

import fr.quinquenaire.projet12joiefull.domain.model.CatalogItems

data class UiState (
    val catalogItemsByCategory: Map<String, List<CatalogItems>> = emptyMap(),
    val selectedItem: CatalogItems? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)