package com.angxing.skytakeout.data.model

data class Category(
    val id: Int,
    val name: String,
    val image: String
)

data class CategoryResponse(
    val code: Int,
    val msg: String?,
    val data: List<Category>
)
