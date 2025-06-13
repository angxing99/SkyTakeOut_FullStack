package com.angxing.skytakeout.data.network

import com.angxing.skytakeout.data.model.BaseResponse
import com.angxing.skytakeout.data.model.CartItem
import com.angxing.skytakeout.data.model.CategoryResponse
import com.angxing.skytakeout.data.model.Dish
import com.angxing.skytakeout.data.model.LoginRequest
import com.angxing.skytakeout.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("user/user/login")
    suspend fun login(@Body request : LoginRequest ) : Response<LoginResponse>

    @GET("/user/category/list?type=1")
    suspend fun getCategories(): Response<CategoryResponse>


    @GET("user/dish/list")
    suspend fun getDishesByCategory(@Query("categoryId") categoryId: Long): Response<BaseResponse<List<Dish>>>


    @POST("/user/shoppingCart/add")
    suspend fun addToCart(@Body body: Map<String, Long>): Response<BaseResponse<Unit>>

    @GET("user/shoppingCart/list")
    suspend fun getCartItems(): Response<BaseResponse<List<CartItem>>>



}