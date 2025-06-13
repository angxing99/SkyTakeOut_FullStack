package com.angxing.skytakeout.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angxing.skytakeout.data.network.AuthApiService

class CartViewModelFactory(
    private val apiService: AuthApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
