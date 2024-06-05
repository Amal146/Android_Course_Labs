package com.example.dobi

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.net.PasswordAuthentication


fun checkUserExists(email: String, password: String, onResult: (Boolean) -> Unit) {
    myRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
        ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    if (user != null && user.pass == password) {
                        // Email exists and password matches
                        cid = FirebaseAuth.getInstance().currentUser?.uid
                        cna = user.name
                        cem = user.email
                        cpwd = user.pass
                        onResult(true)
                        return
                    }
                }
            }
            // Either email doesn't exist or password doesn't match
            onResult(false)
        }

        override fun onCancelled(error: DatabaseError) {
            // Error occurred
            onResult(false)
        }
    })
}

var cid :String? = ""
var cna = ""
var cem = ""
var cpwd = ""


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


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
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
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
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(240, 244, 246, 255))
                            .padding(16.dp),
                        decorationBox = { innerTextField ->
                            if (email.isEmpty()) {
                                Text(text = "Email", color = Color.Gray)
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



                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            checkUserExists(email, password) { exists ->
                                if (exists) {
                                    // Navigate to home page if user exists and password is correct
                                    navController.navigate(Screen.Home.rout)
                                } else {
                                    // Show error message if user doesn't exist or password is incorrect
                                    // You can set an error message state variable to display it in the UI
                                    errorMessage = "Invalid email or password"
                                }
                            }
                        },
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
                    if (errorMessage.isNotEmpty()) {
                        Text(text = errorMessage, color = Color.Red)
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