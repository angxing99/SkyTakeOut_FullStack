package com.angxing.skytakeout.ui.screen.dish

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angxing.skytakeout.ui.component.CustomBottomBar
import com.angxing.skytakeout.ui.component.SearchBar
import com.angxing.skytakeout.ui.component.Topbar
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.angxing.skytakeout.data.network.RetrofitInstance
import com.angxing.skytakeout.ui.component.DishCard
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DishScreen(categoryId: Long, navController: NavController) {
    val viewModel: DishViewModel = viewModel(factory = DishViewModelFactory(RetrofitInstance.api))
    val dishes = viewModel.dishes.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Fetch dishes when screen loads
    LaunchedEffect(Unit) {
        viewModel.fetchDishesByCategory(categoryId)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { CustomBottomBar(navController) }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Topbar()
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(20.dp))
            Text("Popular Dish", color = Color.White, fontSize = 16.sp)

            LazyColumn {
                items(dishes) { dish ->
                    DishCard(dish = dish, onAddToCart = { dishId ->
                        viewModel.addDishToCart(
                            dishId = dishId,
                            onSuccess = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Added to cart")
                                }
                            },
                            onFailure = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Failed to add to cart")
                                }
                            }
                        )
                    })
                }
            }
        }
    }
}
