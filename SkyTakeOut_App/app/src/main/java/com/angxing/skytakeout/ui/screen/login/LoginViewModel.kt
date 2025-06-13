package com.angxing.skytakeout.ui.screen.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angxing.skytakeout.data.model.LoginData
import com.angxing.skytakeout.data.model.LoginRequest
import com.angxing.skytakeout.data.network.AuthApiService
import com.angxing.skytakeout.data.network.TokenManager
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: AuthApiService) : ViewModel() {

    var loginSuccess = mutableStateOf<LoginData?>(null)
        private set

    var loginError = mutableStateOf<String?>(null)
        private set


    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            try {
                val request = LoginRequest(email, password)
                val response = apiService.login(request)

                if (response.isSuccessful && response.body()?.data != null) {
                    val loginData = response.body()!!.data

                    // Save the token using context
                    if (loginData != null) {
                        TokenManager.saveToken(context, loginData.token)
                    }

                    Log.d("LoginViewModel", "Login successful: $loginData")
                    loginSuccess.value = loginData
                    loginError.value = null
                } else {
                    loginError.value = "Login failed: ${response.body()?.msg ?: "Unknown error"}"
                }
            } catch (e: Exception) {
                loginError.value = "Exception: ${e.message}"
                Log.e("LoginViewModel", "Login exception", e)
            }
        }
    }


}
