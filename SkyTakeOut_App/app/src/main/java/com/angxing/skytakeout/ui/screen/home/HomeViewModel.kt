package com.angxing.skytakeout.ui.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angxing.skytakeout.data.model.Category
import com.angxing.skytakeout.data.network.AuthApiService
import com.angxing.skytakeout.data.network.TokenManager
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: AuthApiService) : ViewModel() {

    val categories = mutableStateOf<List<Category>>(emptyList())

    init {
        Log.d("HomeViewModel", "INIT CALLED")

        val token = TokenManager.token
        Log.d("HomeViewModel", "Current Token: $token")  // ✅ Log the token here

        fetchCategories()
    }


    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                Log.d("HomeViewModel", "Calling getCategories()...")
                val response = apiService.getCategories()
                Log.d("HomeViewModel", "Response: $response")

                if (response.isSuccessful) {
                    categories.value = response.body()?.data ?: emptyList()
                    Log.d("HomeViewModel", "Fetched categories: ${categories.value}")
                } else {
                    Log.e("HomeViewModel", "Error body: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception during fetch: ${e.message}", e)
            }
        }
    }


}