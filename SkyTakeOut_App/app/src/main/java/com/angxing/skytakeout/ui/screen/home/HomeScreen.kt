package com.angxing.skytakeout.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.angxing.skytakeout.data.network.RetrofitInstance
import com.angxing.skytakeout.ui.component.CustomBottomBar
import com.angxing.skytakeout.ui.component.DynamicCategoryRow
import com.angxing.skytakeout.ui.component.PopularMenuRow
import com.angxing.skytakeout.ui.component.PromoBanner
import com.angxing.skytakeout.ui.component.Topbar
import com.angxing.skytakeout.ui.component.SearchBar
import com.angxing.skytakeout.ui.component.SectionHeader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(RetrofitInstance.api)
    )
    val categories = viewModel.categories.value

    Scaffold (
        bottomBar = { CustomBottomBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(16.dp)
        ) {
            Topbar()

            Spacer(modifier = Modifier.height(20.dp))
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))
            PromoBanner()

            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = "Popular Category",
                onViewMoreClick = {
                    navController.navigate("category") // or handle specific case
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            DynamicCategoryRow(categories = categories) // ✅ replaced here

            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("Popular Menu")

            Spacer(modifier = Modifier.height(16.dp))
            PopularMenuRow()

        }

    }





}