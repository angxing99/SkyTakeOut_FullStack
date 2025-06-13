package com.angxing.skytakeout.data.model

data class BaseResponse<T>(
    val code: Int,
    val msg: String?,
    val data: T
)