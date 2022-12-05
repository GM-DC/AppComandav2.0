package com.example.appcomandav20.features.orders.data.remote

import com.example.appcomandav20.features.orders.data.remote.dto.CategoryDTO
import retrofit2.http.GET

interface CategoryApi {
    @GET("/api/TablasBasicas/Detail?filter=codigo eq 'CATE_PROD' and referencia4 eq 'R' and ESTADO eq '1'&select=codigo,nombre,numero&orderby=nombre asc")
    suspend fun getCategory() : List<CategoryDTO>
}