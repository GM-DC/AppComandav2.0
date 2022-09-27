package com.example.appcomandav20.domain.model

import com.example.appcomandav20.data.source.remote.dto.LoginUserDTO

data class LoginUserModel(
    val usuariomozo: String,
    var passmozo: String,
)

fun LoginUserModel.toLoginUserDTO(): LoginUserDTO {
    return  LoginUserDTO(
        usuariomozo = usuariomozo,
        passmozo = passmozo
    )
}