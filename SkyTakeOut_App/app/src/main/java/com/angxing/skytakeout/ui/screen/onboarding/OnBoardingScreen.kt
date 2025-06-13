package com.angxing.skytakeout.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel = viewModel(),
    onFinish: () -> Unit
){
    val pages = viewModel.pages
    var pageState by remember { mutableIntStateOf(0) }
    val page = pages[pageState]

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(16.dp),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ){
            Spacer(modifier = Modifier.height(32.dp))

            Image(painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(400.dp),
                contentScale = ContentScale.Fit
                )

            Text(
                text = page.title,
                color = Color.White,
                fontSize =28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp, // ✅ adds vertical spacing between lines
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = page.description,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                        lineHeight = 30.sp, // ✅ adds vertical spacing between lines

            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if(pageState < pages.lastIndex ){
                        pageState++
                    }
                    else{
                        onFinish()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853) ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(56.dp).width(180.dp)
            ){
                Text("Next", color = Color.White, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))


        }



    }





}