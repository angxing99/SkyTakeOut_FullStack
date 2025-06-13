package com.angxing.skytakeout.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angxing.skytakeout.data.network.RetrofitInstance

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(RetrofitInstance.api) as T
    }
}
