package com.example.dobi

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import java.net.PasswordAuthentication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            modifier.fillMaxSize()
            TopAppBar(
                title = {
                    Text(text = "Welcome to Dobi !")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(188, 229, 95, 255),
                    titleContentColor = Color(255,255,255) ,
                )
            )
        },
        content = {
            paddingValues ->
            Column(
                modifier = Modifier.background(color = Color(254, 254, 190, 255))
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF8BC34A)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "SIGN UP",
                        color = Color.White,
                        modifier = Modifier.clickable {navController.navigate(Screen.Signup.rout)},
                        )
                    Text(text = "LOGIN",
                        color = Color.White ,
                        textDecoration = TextDecoration.Underline)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.drop), // Replace with your own drawable resource
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clickable { }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Column (
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    
                    BasicTextField(
                        value = username,
                        onValueChange = { username = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(240, 244, 246, 255))
                            .padding(16.dp),
                        decorationBox = { innerTextField ->
                            if (username.isEmpty()) {
                                Text(text = "Username", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(240, 244, 246, 255))
                            .padding(16.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        decorationBox = { innerTextField ->
                            if (password.isEmpty()) {
                                Text(text = "Password", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it }
                            )
                            Text(text = "Remember me")
                        }
                        Spacer(modifier = Modifier.width(72.dp))

                        Text(text = "Forgot Password", color = Color.Blue)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {navController.navigate(Screen.Home.rout)},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                138,
                                194,
                                74,
                                255
                            )
                        ),
                    ) {
                        Text(text = "Login", color = Color.White)
                    }
                }
            }

        }
    )
}


@Preview
@Composable
fun LoginPreview() {
        LoginScreen(navController = rememberNavController())
}