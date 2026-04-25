package fr.quinquenaire.projet12joiefull.presentation.ui

import fr.quinquenaire.projet12joiefull.domain.model.CatalogItems

/**
 * Represents a section of the catalog grouped by category.
 *
 * @property name The display name of the category.
 * @property items The list of catalog items associated with this category.
 */
data class CategorySection(
    val name: String,
    val items: List<CatalogItems>
)

/**
 * Represents the UI state for the catalog screen.
 *
 * @property categories The list of categorized items to be displayed in the catalog.
 * @property selectedItem The currently selected item, or null if no item is selected.
 * @property isLoading Indicates whether the catalog data is currently being loaded.
 * @property error A message describing any error that occurred, or null if there are no errors.
 */
data class CatalogUiState (
    val categories: List<CategorySection> = emptyList(),
    val selectedItem: CatalogItems? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
