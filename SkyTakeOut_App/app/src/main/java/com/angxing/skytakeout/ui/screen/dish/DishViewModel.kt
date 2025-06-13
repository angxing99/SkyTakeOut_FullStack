package com.angxing.skytakeout.ui.screen.dish

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angxing.skytakeout.data.model.Dish
import com.angxing.skytakeout.data.network.AuthApiService
import kotlinx.coroutines.launch

class DishViewModel(private val apiService: AuthApiService) : ViewModel() {

    val dishes = mutableStateOf<List<Dish>>(emptyList())
    val error = mutableStateOf<String?>(null)

    fun fetchDishesByCategory(categoryId: Long) {
        viewModelScope.launch {
            try {
                Log.d("DishViewModel", "Fetching dishes for categoryId: $categoryId")
                val response = apiService.getDishesByCategory(categoryId)
                if (response.isSuccessful) {
                    dishes.value = response.body()?.data ?: emptyList()
                } else {
                    error.value = response.errorBody()?.string()
                }
            } catch (e: Exception) {
                error.value = e.message
                Log.e("DishViewModel", "Exception: ${e.message}")
            }
        }
    }

    fun addDishToCart(
        dishId: Long,
        onSuccess: () -> Unit = {},
        onFailure: () -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                val response = apiService.addToCart(mapOf("dishId" to dishId))
                if (response.isSuccessful && response.body()?.code == 1) {
                    onSuccess()
                    Log.d("DishViewModel", "Dish $dishId added to cart.")
                } else {
                    onFailure()
                    Log.e("DishViewModel", "Add to cart failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                onFailure()
                Log.e("DishViewModel", "Exception adding to cart: ${e.message}")
            }
        }
    }



}