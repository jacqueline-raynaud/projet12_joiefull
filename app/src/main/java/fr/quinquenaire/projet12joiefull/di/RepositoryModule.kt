package fr.quinquenaire.projet12joiefull.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.quinquenaire.projet12joiefull.data.repository.RepositoryImpl
import fr.quinquenaire.projet12joiefull.domain.repository.CatalogItemsRepository
import javax.inject.Singleton

/**
 * Dagger module used to provide repository implementations for dependency injection.
 *
 * This module binds the [CatalogItemsRepository] interface to its concrete
 * implementation, [RepositoryImpl]. It is installed in the [SingletonComponent]
 * to ensure that repository instances are treated as singletons and persist
 * throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatalogItemsRepository(
        impl: RepositoryImpl
    ): CatalogItemsRepository
}