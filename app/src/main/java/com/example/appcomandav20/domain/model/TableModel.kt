package com.example.appcomandav20.domain.model

import com.google.gson.annotations.SerializedName

data class TableModel(
    val idMesa: Int,
    val estado: String,
    val estadoTrans: String,
    val idZona: String,
    val secuencia: Int?,
    val tipo: String,
    val idPedido: String?,
    val NombreMozo: String?
)
