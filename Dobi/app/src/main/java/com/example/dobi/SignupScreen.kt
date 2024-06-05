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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.net.PasswordAuthentication


data class User(
    val name: String = "",
    val email: String = "",
    val pass: String = "",
    val profileImageRes: Int = 0
)


val database = FirebaseDatabase.getInstance()
val myRef = database.getReference("User")

fun saveUser(user: User, onResult: (Boolean) -> Unit) {

    val userKey = user.email.replace(".", ",")
    myRef.child(userKey).setValue(user)
        .addOnSuccessListener {
            Log.d("Firebase", "User profile saved successfully")
            onResult(true)
        }
        .addOnFailureListener {
            Log.d("Firebase", "Failed to save user profile")
            onResult(false)
        }
}


fun checkUsernameExists(username: String, onResult: (Boolean) -> Unit) {

    myRef.orderByChild("name").equalTo(username).addListenerForSingleValueEvent(object :
        ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            onResult(snapshot.exists())
        }

        override fun onCancelled(error: DatabaseError) {
            onResult(false)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController , modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

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
                modifier = Modifier
                    .background(color = Color(254, 254, 190, 255))
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
                    Text(text = "SIGN UP", color = Color.White, textDecoration = TextDecoration.Underline)
                    Text(
                        text = "LOGIN",
                        color = Color.White,
                        modifier = Modifier.clickable {navController.navigate(Screen.Login.rout)},
                    )
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
                        onValueChange = {
                            email = it
                            emailError = if (validateEmail(email)) null else "Invalid email format"
                        },
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

                    if (emailError != null) {
                        Text(text = emailError ?: "", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    BasicTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            usernameError = null
                        },
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

                    if (usernameError != null) {
                        Text(text = usernameError ?: "", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    BasicTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = if (validatePassword(password)) null else "Password must be at least 6 characters"
                        },
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

                    if (passwordError != null) {
                        Text(text = passwordError ?: "", color = Color.Red, fontSize = 12.sp)
                    }


                    Spacer(modifier = Modifier.height(24.dp))

                    Button(

                        onClick = {
                            loading = true
                            message = ""
                            if (!validateEmail(email)) {
                                emailError = "Invalid email format"
                                loading = false
                                return@Button
                            }
                            if (!validatePassword(password)) {
                                passwordError = "Password must be at least 6 characters"
                                loading = false
                                return@Button
                            }
                            checkUsernameExists(username) { exists ->
                                if (exists) {
                                    usernameError = "Username already exists"
                                    loading = false
                                } else {
                                    val user = User(
                                        name = username,
                                        email = email,
                                        pass = password,
                                        profileImageRes = R.drawable.drop
                                    )

                                    saveUser(user) { success ->
                                        loading = false
                                        if (success) {
                                            navController.navigate("login")
                                        } else {
                                            message = "Failed to save user profile"
                                        }
                                    }
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
                        ), enabled = !loading
                    ) {
                        if (loading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp))
                        } else {
                            Text(text = "Sign Up", color = Color.White)
                        }
                    }

                    if (message.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = message, color = Color.Red)
                    }
                }
            }
        }
    )
}




@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(navController = rememberNavController())
}