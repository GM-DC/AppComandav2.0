package com.example.appcomandav20.di

import com.example.appcomandav20.data.repositories.*
import com.example.appcomandav20.domain.repositories.*
import dagger.hilt.android.components.ViewModelComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindUsuarioRepository(impl: UsuarioRepositoryImpl): UsuarioRepository

    @Binds
    abstract fun bindLoginUserRepository(impl: LoginUserRepositoryImpl): LoginUserRepository

    @Binds
    abstract fun bindZoneRepository(impl: ZoneRepositoryImpl): ZonesRepository

    @Binds
    abstract fun bindTableRepository(impl: TableRespositoryImpl): TablesRepository

    @Binds
    abstract fun bindCategoryRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun bindDishRepository(impl: DishRepositoryImpl): DishRepository


}