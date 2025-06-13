package com.angxing.skytakeout.data.model

data class CartItem(
    val id: Long,
    val name: String,
    val userId: Long,
    val dishId: Long?,
    val setmealId: Long?,
    val dishFlavor: String?,
    val number: Int,
    val amount: Double,
    val image: String,
    val createTime: String
)
