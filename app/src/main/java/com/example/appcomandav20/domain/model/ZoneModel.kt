package com.example.appcomandav20.domain.model

import com.google.gson.annotations.SerializedName

data class ZoneModel(
    @SerializedName("Nombre") val nombreZonas: String,
    @SerializedName("Numero") val idZona: String,
)