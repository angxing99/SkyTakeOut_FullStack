package com.angxing.skytakeout.ui.screen.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angxing.skytakeout.ui.component.CustomBottomBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angxing.skytakeout.data.network.RetrofitInstance
import androidx.compose.runtime.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(navController: NavController) {
    val viewModel: CartViewModel = viewModel(factory = CartViewModelFactory(RetrofitInstance.api))
    val cartItems = viewModel.cartItems.value // ✅ correct
    val totalPrice = viewModel.totalAmount.doubleValue


    LaunchedEffect(Unit) {
        viewModel.fetchCartItems()
    }

    Scaffold(
        bottomBar = { CustomBottomBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Order details", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            cartItems.forEach { item ->
                CartItemCard(
                    name = item.name ?: "Unknown",
                    vendor = item.name ?: "Vendor",
                    price = item.amount ?: 0.0,
                    imageUrl = item.image ?: "",
                    quantity = item.number ?: 1
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

            Spacer(modifier = Modifier.height(44.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF53E88B), shape = RoundedCornerShape(24.dp))
                    .padding(24.dp)
            ) {
                Column {
                  //  SummaryRow("Sub-Total", "$${totalPrice}")
                    SummaryRow("Delivery Charge", "$0",)
                    Spacer(modifier = Modifier.height(20.dp))
                    SummaryRow("Discount", "$0")
                    Spacer(modifier = Modifier.height(20.dp))

                    SummaryRow("Total", "$${totalPrice}")
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* TODO: Handle place order */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Place My Order", color = Color(0xFF53E88B), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}



@Composable
fun CartItemCard(name: String, vendor: String, price: Double, imageUrl: String, quantity: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, color = Color.White, fontWeight = FontWeight.Bold)
           // Text(vendor, color = Color.Gray, fontSize = 12.sp)
            Text("$${price.toInt()}", color = Color(0xFF53E88B), fontWeight = FontWeight.Bold)

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: decrease */ }) {
                    Icon(Icons.Default.Remove, contentDescription = null, tint = Color.White)
                }
                Text(quantity.toString(), color = Color.White)
                IconButton(onClick = { /* TODO: increase */ }) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}



@Composable
fun SummaryRow(label: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White, fontSize = 20.sp)
        Text(amount, color = Color.White, fontSize = 20.sp)
    }
}
