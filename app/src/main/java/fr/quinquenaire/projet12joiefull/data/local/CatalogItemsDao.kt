package fr.quinquenaire.projet12joiefull.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the "catalog" table.
 *
 * This interface defines the database operations for [CatalogItemsEntity], including
 * methods for retrieving items, tracking favorites, and managing user-generated
 * content such as ratings and comments.
 */
@Dao
interface CatalogItemsDao {

    // --  read --

    @Query("SELECT * FROM catalog")
    fun getAll(): Flow<List<CatalogItemsEntity>>

    @Query("SELECT * FROM catalog WHERE id = :id")
    fun getById(id: Long): Flow<CatalogItemsEntity>

    @Query("SELECT COUNT(*) FROM catalog")
    suspend fun getCount(): Int

    // -- initial populate --

    @Insert
    suspend fun insertAll(catalogItems: List<CatalogItemsEntity>)

    // -- user actions --

    @Query("UPDATE catalog SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Long)

    @Query("UPDATE catalog SET userRating = :userRating WHERE id = :id")
    suspend fun updateUserRating(id: Long, userRating: Float)

    @Query("UPDATE catalog SET userComment = :userComment WHERE id = :id")
    suspend fun updateUserComment(id: Long, userComment: String)
}
