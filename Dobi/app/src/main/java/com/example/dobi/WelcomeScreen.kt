package com.example.dobi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Welcome(navController: NavHostController, modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .background(color = Color(255, 255, 153))
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Welcome!",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 72.sp,
            color = Color(140,189,62)
        )
        Text(
            text = "Together to Fight Diabetes !!\n" +
                    "Dobi with you to check your \n" +
                    "Diabetes level , get advice and remind " +
                    "your medicines \n" +
                    "",
            fontFamily = FontFamily.SansSerif,
            fontSize = 28.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold
        )
        Image(
            painter = painterResource(id = R.drawable.drop) ,
            contentDescription = "",
            Modifier
                .height(350.dp)
                .clickable{navController.navigate(Screen.Home.rout)}
            ,
        )

    }

}

