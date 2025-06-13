package com.angxing.skytakeout.data.network

import com.angxing.skytakeout.data.Constants
import com.angxing.skytakeout.data.network.TokenManager.token
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object RetrofitInstance {

    private val authInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()

        // Add in token from login as interceptor
        TokenManager.token?.let { token ->
            requestBuilder.addHeader("authentication", token)
        }

        chain.proceed(requestBuilder.build())
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
}
