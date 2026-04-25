package fr.quinquenaire.projet12joiefull.domain.repository

import fr.quinquenaire.projet12joiefull.domain.model.CatalogItems
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing catalog items.
 * Provides methods for retrieving and updating catalog items.
 * [ensureDataAvailable] Initialize the database from the local JSON file
 * only if no elements are present in SQLite storage
 */
interface CatalogItemsRepository {
    fun getCatalogItemsList(): Flow<List<CatalogItems>>
    fun getCatalogItemsById(id:Long) : Flow<CatalogItems>

    // -- user actions --
    suspend fun updateUserRating(id: Long, rating: Float)
    suspend fun toggleFavorite(id: Long)
    suspend fun updateUserComment(id: Long, comment: String)


    // -- Json if room is empty --
    suspend fun ensureDataAvailable()
}
