package com.angxing.skytakeout.data.model

data class LoginResponse(
    val code: Int,
    val msg: String?,
    val data: LoginData?
)

data class LoginData(
    val id: Int,
    val openid: String,
    val token : String
)
