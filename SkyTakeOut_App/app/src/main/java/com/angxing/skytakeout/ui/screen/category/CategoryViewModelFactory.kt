package com.angxing.skytakeout.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angxing.skytakeout.data.network.AuthApiService

class CategoryViewModelFactory(
    private val apiService: AuthApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(apiService) as T
    }
}
