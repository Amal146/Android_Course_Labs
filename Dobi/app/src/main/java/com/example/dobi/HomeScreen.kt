package com.example.dobi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dobi.ui.theme.DobiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            modifier.fillMaxSize()
            TopAppBar(
                title = {
                    Text(text = "Home" )
                },
                actions = {
                    IconButton(onClick = {navController.navigate(Screen.Profile.rout)}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(48.dp)
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            tint = Color.White,

                            )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(139, 188, 62, 255),
                    titleContentColor = Color(255,255,255) ,
                )

            )
        }
    ) {
            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(254, 242, 172, 255)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = modifier
                        .padding(horizontal = 32.dp)
                        .height(150.dp)
                        .width(400.dp),
                    shape = RoundedCornerShape(48.dp),
                    onClick = {navController.navigate(Screen.Checker.rout)},
                    colors = CardDefaults.cardColors(
                        containerColor = Color(33, 148, 239, 255),
                        contentColor = Color.White
                    )

                ) {
                    Text(
                        text = "  Diabetes Level Checker \n \n 🩺",
                        modifier = modifier
                            .padding(vertical = 40.dp),
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center


                    )
                }
                Spacer(modifier = modifier.height(100.dp))
                Card(
                    modifier = modifier
                        .padding(horizontal = 32.dp)
                        .height(150.dp)
                        .width(400.dp),
                    shape = RoundedCornerShape(48.dp),
                    onClick = {navController.navigate(Screen.Reminder.rout)},
                    colors = CardDefaults.cardColors(
                        containerColor = Color(239, 98, 145, 255),
                        contentColor = Color.White,

                        )
                ) {
                    Text(
                        text = "Medication Reminder \n \n" +
                                "                 ⏰",
                        modifier = modifier
                            .wrapContentSize(Alignment.Center)
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(vertical = 34.dp),
                        fontSize = 28.sp
                    )
                }
                Spacer(modifier = modifier.height(100.dp))
                Card(
                    modifier = modifier
                        .padding(horizontal = 32.dp)
                        .height(150.dp)
                        .width(400.dp),
                    shape = RoundedCornerShape(48.dp),
                    onClick = {navController.navigate(Screen.Adviser.rout)},
                    colors = CardDefaults.cardColors(
                        containerColor = Color(80, 180, 166, 255),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Dobi Advice 🤓",
                        modifier = modifier
                            .wrapContentSize(Alignment.Center)
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(vertical = 55.dp),
                        fontSize = 28.sp

                    )
                }
            }

        }
    }







@Preview
@Composable
fun viewHome(){
    DobiTheme(darkTheme = true) {
        Home(navController = rememberNavController())
    }
}