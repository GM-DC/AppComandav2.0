package com.example.appcomandav20.features.orders.data.remote.dto

import com.example.appcomandav20.features.orders.domain.model.CategoryModel
import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("Nombre") val nameCategoria: String,
    @SerializedName("Numero") val idCategoria: String
)

fun CategoryDTO.toCategoryModel(): CategoryModel {
    return  CategoryModel(
        nameCategoria = nameCategoria,
        idCategoria = idCategoria
    )
}