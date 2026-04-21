package fr.quinquenaire.projet12joiefull.data.repository

import fr.quinquenaire.projet12joiefull.data.local.CatalogItemsDao
import fr.quinquenaire.projet12joiefull.data.remote.CatalogItemsApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    private lateinit var dao: CatalogItemsDao
    private lateinit var apiService: CatalogItemsApiService
    private lateinit var repository: RepositoryImpl

    @Before
    fun setup() {
        dao = mockk(relaxed = true) // relaxed=true permet de ne pas mocker chaque appel void/unit
        apiService = mockk()
        repository = RepositoryImpl(dao, apiService)
    }

    @Test
    fun `ensureDataAvailable calls API and inserts`() = runTest {
        // GIVEN
        coEvery { apiService.getCatalogItemsList() } returns emptyList()

        // WHEN: On demande de s'assurer que les données sont là
        repository.ensureDataAvailable()

        // THEN: L'API doit avoir été appelée et l'insertion faite
        coVerify(exactly = 1) { apiService.getCatalogItemsList() }
        coVerify(exactly = 1) { dao.insertAll(any()) }
    }

    @Test
    fun `updateUserComment calls dao`() = runTest {
        // GIVEN
        val id = 1L
        val comment = "Great!"

        // WHEN
        repository.updateUserComment(id, comment)

        // THEN
        coVerify { dao.updateUserComment(id, comment) }
    }

    @Test
    fun `updateUserRating calls dao`() = runTest {
        // GIVEN
        val id = 1L
        val rating = 4.5f

        // WHEN
        repository.updateUserRating(id, rating)

        // THEN
        coVerify { dao.updateUserRating(id, rating) }
    }

    @Test
    fun `toggleFavorite calls dao`() = runTest {
        // GIVEN
        val id = 1L

        // WHEN
        repository.toggleFavorite(id)

        // THEN
        coVerify { dao.toggleFavorite(id) }
    }
}
