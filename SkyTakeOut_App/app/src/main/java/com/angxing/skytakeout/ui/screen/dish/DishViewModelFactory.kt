package com.angxing.skytakeout.ui.screen.dish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angxing.skytakeout.data.network.AuthApiService


class DishViewModelFactory(
    private val apiService: AuthApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DishViewModel(apiService) as T
    }
}
