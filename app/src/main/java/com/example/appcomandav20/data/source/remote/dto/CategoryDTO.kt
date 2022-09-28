package com.example.appcomandav20.data.source.remote.dto

import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.UsuarioDC
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