package com.example.appcomandav20.features.users.domain.model

data class ListUsuarioModel(
    val listUsuarioModel: List<UsuarioDC>
)

data class  UsuarioDC (
    val codUsuario: String,
    val nombreUsuario: String
)


