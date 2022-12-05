package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.CategoryApi
import com.example.appcomandav20.features.orders.data.remote.dto.toCategoryModel
import com.example.appcomandav20.features.orders.domain.model.CategoryModel
import com.example.appcomandav20.features.orders.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoryApi
) : CategoriesRepository {
    override fun getCategories(): Flow<NetworkResult<List<CategoryModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listCategories: MutableList<CategoryModel> = mutableListOf()
            api.getCategory().forEach { listCategories.add(it.toCategoryModel()) }
            emit(NetworkResult.Success( listCategories ))
        } catch (e: HttpException) {
            emit(
                NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(
                NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }
}