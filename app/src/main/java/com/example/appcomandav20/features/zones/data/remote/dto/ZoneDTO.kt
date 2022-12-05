package com.example.appcomandav20.features.zones.data.remote.dto

import com.example.appcomandav20.features.zones.domain.model.ZoneModel
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