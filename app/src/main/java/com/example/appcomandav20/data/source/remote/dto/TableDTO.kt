package com.example.appcomandav20.data.source.remote.dto

import com.example.appcomandav20.domain.model.TableModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.google.gson.annotations.SerializedName

data class TableDTO (
    @SerializedName("idMesa") val idMesa: Int,
    val estado: String,
    val estadoTrans: String,
    @SerializedName("piso") val idZona: String,
    val secuencia: Int?,
    val tipo: String,
    val idPedido: String?,
    val NombreMozo: String?
)

fun TableDTO.toTableModel(): TableModel {
    return  TableModel(
    idMesa = idMesa,
    estado = estado,
    estadoTrans = estadoTrans,
    idZona = idZona,
    secuencia = secuencia,
    tipo = tipo,
    idPedido = idPedido,
    NombreMozo = NombreMozo)
}