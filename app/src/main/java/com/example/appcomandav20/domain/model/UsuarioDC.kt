package com.example.appcomandav20.domain.model

import com.example.appcomandav20.data.source.remote.dto.UsuarioDTO

data class ListUsuarioModel(
    val listUsuarioModel: List<UsuarioDC>
)

data class  UsuarioDC (
    val codUsuario: String,
    val nombreUsuario: String
)


