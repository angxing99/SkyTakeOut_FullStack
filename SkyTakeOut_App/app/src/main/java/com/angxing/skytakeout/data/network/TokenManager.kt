package com.angxing.skytakeout.data.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_prefs")

object TokenManager {
    private const val TOKEN_KEY = "auth_token"
    private val Context.dataStore by preferencesDataStore("user_prefs")

    var token: String? = null

    suspend fun saveToken(context: Context, value: String) {
        token = value // save in memory
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(TOKEN_KEY)] = value
        }
    }

    suspend fun loadToken(context: Context) {
        val prefs = context.dataStore.data.first()
        token = prefs[stringPreferencesKey(TOKEN_KEY)]
    }

    suspend fun clearToken(context: Context) {
        token = null
        context.dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(TOKEN_KEY))
        }
    }
}
