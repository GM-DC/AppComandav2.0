package com.example.appcomandav20.features.passcode.domain.model

import com.example.appcomandav20.features.orders.data.remote.dto.LoginUserDTO

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