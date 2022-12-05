package com.example.appcomandav20.di

import com.example.appcomandav20.features.orders.data.resouce.*
import com.example.appcomandav20.features.orders.domain.repositories.*
import com.example.appcomandav20.features.passcode.data.resouce.LoginUserRepositoryImpl
import com.example.appcomandav20.features.passcode.domain.repository.LoginUserRepository
import com.example.appcomandav20.features.users.data.resouce.UsuarioRepositoryImpl
import com.example.appcomandav20.features.users.domain.repository.UsuarioRepository
import com.example.appcomandav20.features.zones.data.resouce.TableRespositoryImpl
import com.example.appcomandav20.features.orders.data.resouce.UpdateStateTableImpl
import com.example.appcomandav20.features.zones.data.resouce.ZoneRepositoryImpl
import com.example.appcomandav20.features.zones.domain.repository.TablesRepository
import com.example.appcomandav20.features.zones.domain.repository.UpdateStateTableRepository
import com.example.appcomandav20.features.zones.domain.repository.ZonesRepository
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

    @Binds
    abstract fun bindSendOrderRepository(impl: SendOrdersRepositoryImpl): SendOrdersRepository

    @Binds
    abstract fun bindOrdersFullfilledRepository(impl: ListOrdersFulfilledRepositoryImpl): ListOrdersFulfilledRepository

    @Binds
    abstract fun bindUpdateStateTable(impl: UpdateStateTableImpl): UpdateStateTableRepository

    @Binds
    abstract fun bindOrder(impl: OrderRepositoryImpl): OrderRepository

    @Binds
    abstract fun bindUpdateColorOrder(impl: UpdateColorOrderImpl): UpdateColorOrderRespository


}