package fr.quinquenaire.projet12joiefull.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The main Room database for the application.
 *
 * This class serves as the primary access point for the underlying SQLite database.
 * It provides the [CatalogItemsDao] to perform operations on the catalog items stored locally.
 */
@Database(
    entities = [CatalogItemsEntity::class],
    version = 1
    )

abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogItemsDao(): CatalogItemsDao
}