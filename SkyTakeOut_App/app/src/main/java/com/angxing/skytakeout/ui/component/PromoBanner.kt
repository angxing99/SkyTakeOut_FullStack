package com.angxing.skytakeout.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.angxing.skytakeout.R

@Composable
fun PromoBanner(){
    Box(
        modifier = Modifier.padding(top = 16.dp)
    ){
        Image(painter = painterResource(id = R.drawable.promo_advertising),  contentDescription = null,   modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Fit )
    }
}