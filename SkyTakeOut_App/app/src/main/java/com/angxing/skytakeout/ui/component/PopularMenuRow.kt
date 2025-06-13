package com.angxing.skytakeout.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angxing.skytakeout.R

@Composable
fun PopularMenuRow(){

    Row(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF2C2C2C), RoundedCornerShape(16.dp)).padding(12.dp)
    ){
        Image(painter = painterResource(R.drawable.donut),  contentDescription = null,   modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit )

        Spacer(modifier = Modifier.width(20.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Column{
                Text(text = "Donut",   color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Warung Herbal",  color = Color.Gray,
                    fontSize = 18.sp)
            }

            Text(text = "$7",    color = Color(0xFFFFAC1C),
                fontWeight = FontWeight.Bold,
                fontSize =22.sp)
        }



    }

}