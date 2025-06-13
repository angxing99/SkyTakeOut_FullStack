package com.angxing.skytakeout.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.angxing.skytakeout.ui.screen.onboarding.OnBoardingScreen
import androidx.navigation.compose.NavHost // ✅ the correct Composable function
import androidx.navigation.compose.composable
import com.angxing.skytakeout.ui.screen.cart.CartScreen
import com.angxing.skytakeout.ui.screen.category.CategoryScreen
import com.angxing.skytakeout.ui.screen.dish.DishScreen
import com.angxing.skytakeout.ui.screen.home.HomeScreen
import com.angxing.skytakeout.ui.screen.login.LoginScreen
import com.angxing.skytakeout.ui.screen.onboarding.SplashScreen

@Composable
fun AppNavGraph(startDestination: String = "login", navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("onboarding") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("onboarding") {
            OnBoardingScreen(onFinish = {
                navController.navigate("login") {
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("category") {
            CategoryScreen(navController = navController)
        }

        composable("dish/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toLongOrNull()
            if (categoryId != null) {
                DishScreen(categoryId = categoryId, navController = navController)
            }
        }

        composable("cart") { CartScreen(navController) }
        composable("profile") { /* ProfileScreen(navController) */ }
        composable("messages") { /* MessagesScreen(navController) */ }



    }
}
