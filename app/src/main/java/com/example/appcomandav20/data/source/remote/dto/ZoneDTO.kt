package com.example.appcomandav20.data.source.remote.dto

import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.model.ZoneModel
import com.google.gson.annotations.SerializedName

data class ZoneDTO (
    @SerializedName("Nombre") val nombreZonas: String,
    @SerializedName("Numero") val idZona: String,
)

fun ZoneDTO.toZoneModel(): ZoneModel {
    return  ZoneModel(
        nombreZonas = nombreZonas,
        idZona = idZona
    )
}