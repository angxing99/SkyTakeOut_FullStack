package com.angxing.skytakeout.data.model

data class Dish(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val price: Double,
    val image: String,
    val description: String?
)
