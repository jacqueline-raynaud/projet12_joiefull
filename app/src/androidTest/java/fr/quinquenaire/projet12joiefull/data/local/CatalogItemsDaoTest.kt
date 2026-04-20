package fr.quinquenaire.projet12joiefull.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CatalogItemsDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: CatalogItemsDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Utilisation d'une base de données en mémoire pour les tests
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.catalogItemsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadAllItems() = runBlocking {
        // GIVEN: Une liste d'entités
        val items = listOf(
            CatalogItemsEntity(
                id = 1,
                name = "Produit Test",
                imageUrl = "url",
                description = "desc",
                category = "cat",
                likes = 0,
                price = 10.0,
                originalPrice = 15.0
            )
        )

        // WHEN: Insertion dans la base
        dao.insertAll(items)

        // THEN: On récupère la liste et on vérifie le contenu
        val allItems = dao.getAll().first()
        assertEquals(1, allItems.size)
        assertEquals("Produit Test", allItems[0].name)
    }

    @Test
    fun toggleFavoriteUpdatesValue() = runBlocking {
        // GIVEN: Un item inséré (non favori par défaut)
        val item = CatalogItemsEntity(
            id = 2, name = "Favori Test", imageUrl = "url", description = "desc",
            category = "cat", likes = 5, price = 20.0, originalPrice = 25.0,
            isFavorite = false
        )
        dao.insertAll(listOf(item))

        // WHEN: On bascule l'état favori
        dao.toggleFavorite(2L)

        // THEN: L'item doit être en favori
        val updatedItem = dao.getById(2L).first()
        assertTrue(updatedItem.isFavorite)
        
        // WHEN: On re-bascule
        dao.toggleFavorite(2L)
        
        // THEN: Il ne doit plus être favori
        val finalItem = dao.getById(2L).first()
        assertEquals(false, finalItem.isFavorite)
    }

    @Test
    fun updateRatingUpdatesValue() = runBlocking {
        // GIVEN: Un item inséré
        val item = CatalogItemsEntity(
            id = 3, name = "Rating Test", imageUrl = "url", description = "desc",
            category = "cat", likes = 5, price = 20.0, originalPrice = 25.0
        )
        dao.insertAll(listOf(item))

        // WHEN: On met à jour la note
        dao.updateUserRating(3L, 4.5f)

        // THEN: La note doit être correcte
        val updatedItem = dao.getById(3L).first()
        assertEquals(4.5f, updatedItem.userRating)
    }
}
