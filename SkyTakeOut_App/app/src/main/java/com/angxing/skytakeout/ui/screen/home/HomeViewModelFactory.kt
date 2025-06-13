package com.angxing.skytakeout.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angxing.skytakeout.data.network.AuthApiService
import com.angxing.skytakeout.data.network.RetrofitInstance

class HomeViewModelFactory(
    private val apiService: AuthApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(apiService) as T
    }
}
