package fr.quinquenaire.projet12joiefull.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.quinquenaire.projet12joiefull.data.repository.RepositoryImpl
import fr.quinquenaire.projet12joiefull.domain.repository.CatalogItemsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatalogItemsRepository(
        impl: RepositoryImpl
    ): CatalogItemsRepository
}