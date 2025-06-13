package com.angxing.skytakeout.ui.screen.cart

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angxing.skytakeout.data.model.CartItem
import com.angxing.skytakeout.data.network.AuthApiService
import kotlinx.coroutines.launch

class CartViewModel(private val apiService: AuthApiService) : ViewModel() {
    val cartItems = mutableStateOf<List<CartItem>>(emptyList())
    val error = mutableStateOf<String?>(null)
    val totalAmount = mutableDoubleStateOf(0.0) // <- Add total amount state

    fun fetchCartItems() {
        viewModelScope.launch {
            try {
                val response = apiService.getCartItems()
                if (response.isSuccessful) {
                    val items = response.body()?.data ?: emptyList()
                    cartItems.value = items

                    // Calculate total
                    totalAmount.doubleValue = items.sumOf { (it.number ?: 0) * (it.amount ?: 0.0) }

                } else {
                    error.value = response.errorBody()?.string()
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}
