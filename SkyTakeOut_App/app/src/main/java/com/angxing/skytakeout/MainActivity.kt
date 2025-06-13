package com.angxing.skytakeout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.angxing.skytakeout.data.network.TokenManager
import com.angxing.skytakeout.ui.navigation.AppNavGraph
import com.angxing.skytakeout.ui.theme.SkyTakeOutTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val startDestination = mutableStateOf("login")

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {

            TokenManager.loadToken(applicationContext)

            // Check if token exists and update startDestination
            if (!TokenManager.token.isNullOrBlank()) {
                startDestination.value = "home"
            }

            // Now launch the UI
            setContent {
                val navController = rememberNavController()

                SkyTakeOutTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        AppNavGraph(startDestination = startDestination.value, navController = navController)
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkyTakeOutTheme {

    }
}