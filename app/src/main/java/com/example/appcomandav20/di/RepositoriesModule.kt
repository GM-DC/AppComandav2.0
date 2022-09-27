package com.example.appcomandav20.di

import com.example.appcomandav20.data.repositories.LoginUserRepositoryImpl
import com.example.appcomandav20.data.repositories.UsuarioRepositoryImpl
import com.example.appcomandav20.domain.repositories.LoginUserRepository
import dagger.hilt.android.components.ViewModelComponent
import com.example.appcomandav20.domain.repositories.UsuarioRepository
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

}