package com.angxing.skytakeout.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.angxing.skytakeout.R
import com.angxing.skytakeout.data.model.LoginData

@Composable
fun LoginScreen(  viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory()),
                  onLoginSuccess: (LoginData) -> Unit ){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(16.dp),
        contentAlignment = Alignment.TopStart
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Image(
                painter = painterResource(id =R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Fit
                )

            Text(text = "Sky Take Out",
                fontSize = 38.sp,
                color = Color(0xff53E88B),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
                )

            Text(text = "Deliver Favourite Food",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))



            Text(text = "Login To Your Account", fontSize = 20.sp, textAlign = TextAlign.Center, color = Color.White)
            Spacer(modifier = Modifier.height(40.dp))

            LoginInputFields(
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    viewModel.login(email, password, context)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853) ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(56.dp).width(180.dp)
            ){
                Text("Login", color = Color.White, fontSize = 20.sp)
            }

            viewModel.loginSuccess.value?.let { loginData ->
                LaunchedEffect(loginData) {
                    onLoginSuccess(loginData)
                }
            }

            viewModel.loginError.value?.let { error ->
                Text("Login failed: $error", color = Color.Red)
            }

        }

    }

}

@Composable
fun LoginInputFields(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = {Text("Email", color = Color.Gray)},
            singleLine = true,
            modifier = Modifier.fillMaxWidth().height(56.dp).width(300.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2C2C),
                focusedContainerColor = Color(0xFF2C2C2C),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),

        )

        TextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {Text("Password", color = Color.Gray)},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().height(56.dp).width(300.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2C2C),
                focusedContainerColor = Color(0xFF2C2C2C),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
        )

    }
}