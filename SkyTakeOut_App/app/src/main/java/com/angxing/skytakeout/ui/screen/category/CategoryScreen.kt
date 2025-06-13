package com.angxing.skytakeout.ui.screen.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.angxing.skytakeout.data.network.RetrofitInstance
import com.angxing.skytakeout.ui.component.CustomBottomBar
import com.angxing.skytakeout.ui.component.SearchBar
import com.angxing.skytakeout.ui.component.Topbar
import com.angxing.skytakeout.ui.screen.home.HomeViewModel
import com.angxing.skytakeout.ui.screen.home.HomeViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(navController: NavController) {
        val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(RetrofitInstance.api)
        )
        val categories = viewModel.categories.value


        Scaffold(
                bottomBar = { CustomBottomBar(navController) }
        ) {
              Column(modifier = Modifier.fillMaxSize().background(color = Color.Black).padding(16.dp))
              {
                      Topbar()

                      Spacer(modifier = Modifier.height(20.dp))
                      SearchBar()

                      Spacer(modifier = Modifier.height(20.dp))
                      Text("Popular Category", color = Color.White, fontSize = 16.sp)


                      Spacer(modifier = Modifier.height(20.dp))
                      LazyVerticalGrid(
                              columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                              modifier = Modifier.fillMaxSize(),
                              verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                              horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                              content = {
                                      items(categories.size) { index ->
                                              val category = categories[index]
                                              CategoryCard(
                                                      title = category.name,
                                                      imageUrl = category.image,


                                                              onClick = {
                                                                      navController.navigate("dish/${category.id}")
                                                      }
                                              )
                                      }
                              }
                      )


              }

      }

}

@Composable
fun CategoryCard(title: String, imageUrl: String, onClick: () -> Unit) {
        Column(
                modifier = Modifier
                        .width(150.dp)
                        .height(150.dp) // 👈 Ensures all cards are the same height
                        .background(Color.DarkGray, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                        .clickable(enabled = true) {
                                onClick.invoke()
                        }
        ) {
                AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                        contentScale = ContentScale.Crop
                )
                Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 2)
        }
}

