package fr.quinquenaire.projet12joiefull.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CatalogItemsEntity::class],
    version = 1
    )

abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogItemsDao(): CatalogItemsDao
}