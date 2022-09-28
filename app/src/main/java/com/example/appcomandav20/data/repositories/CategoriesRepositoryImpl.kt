package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.CategoryApi
import com.example.appcomandav20.data.source.remote.UserLoginApi
import com.example.appcomandav20.data.source.remote.dto.toCategoryModel
import com.example.appcomandav20.data.source.remote.dto.toUsuarioDC
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.CategoriesRepository
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
            emit(NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }
}