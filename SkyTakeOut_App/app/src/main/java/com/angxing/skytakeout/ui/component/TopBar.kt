package com.angxing.skytakeout.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Topbar(){

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Text(text ="Find Your\nFavourite Food", color= Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            lineHeight = 30.sp
            )

        Icon(
           imageVector = Icons.Default.Notifications,
            contentDescription = "Notification",
            tint = Color(0xff53E88B)
        )
    }

}