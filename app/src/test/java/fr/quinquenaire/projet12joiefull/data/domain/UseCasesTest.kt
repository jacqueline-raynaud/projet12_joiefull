package fr.quinquenaire.projet12joiefull.data.domain

import fr.quinquenaire.projet12joiefull.domain.repository.CatalogItemsRepository
import fr.quinquenaire.projet12joiefull.domain.usecases.CommentItem
import fr.quinquenaire.projet12joiefull.domain.usecases.EnsureDataAvailable
import fr.quinquenaire.projet12joiefull.domain.usecases.GetCatalogItemsById
import fr.quinquenaire.projet12joiefull.domain.usecases.GetCatalogItemsList
import fr.quinquenaire.projet12joiefull.domain.usecases.ToggleFavorite
import fr.quinquenaire.projet12joiefull.domain.usecases.UpdateRating
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UseCasesTest {

    private val repository = mockk<CatalogItemsRepository>(relaxed = true)

    @Test
    fun `CommentItem calls repository with correct parameters`() = runTest {
        val useCase = CommentItem(repository)
        val id = 42L
        val comment = "Super article"

        useCase(id, comment)

        coVerify { repository.updateUserComment(id, comment) }
    }

    @Test
    fun `UpdateRating calls repository with correct parameters`() = runTest {
        val useCase = UpdateRating(repository)
        val id = 42L
        val rating = 4.5f

        useCase(id, rating)

        coVerify { repository.updateUserRating(id, rating) }
    }

    @Test
    fun `ToggleFavorite calls repository with correct parameters`() = runTest {
        val useCase = ToggleFavorite(repository)
        val id = 42L

        useCase(id)

        coVerify { repository.toggleFavorite(id) }
    }

    @Test
    fun `EnsureDataAvailable calls repository`() = runTest {
        val useCase = EnsureDataAvailable(repository)

        useCase()

        coVerify { repository.ensureDataAvailable() }
    }

    @Test
    fun `GetCatalogItemsById calls repository with correct id`() {
        val useCase = GetCatalogItemsById(repository)
        val id = 42L
        every { repository.getCatalogItemsById(id) } returns flowOf(mockk())

        useCase(id)

        verify { repository.getCatalogItemsById(id) }
    }

    @Test
    fun `GetCatalogItemsList calls repository`() {
        val useCase = GetCatalogItemsList(repository)
        every { repository.getCatalogItemsList() } returns flowOf(emptyList())

        useCase()

        verify { repository.getCatalogItemsList() }
    }
}
