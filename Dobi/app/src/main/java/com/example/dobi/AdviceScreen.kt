package com.example.dobi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

val advicesRef = database.getReference("Advices")



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Adviser(navController: NavHostController, modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            modifier.fillMaxSize()
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Beck",
                            modifier = Modifier
                                .size(48.dp)
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            tint = Color.White,
                        )
                    }
                },
                title = {
                    Text(text = "Advice Dose 🤓 " , textAlign = TextAlign.Center)
                }
                ,

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(80, 180, 166, 255),
                    titleContentColor = Color(255,255,255) ,
                )

            )
        }
    ) {
        AdviserScreen(modifier = modifier.padding(it))
    }
}



@Composable
fun AdviserScreen(modifier: Modifier = Modifier){

    // State to hold the current advice
        var currentAdvice by remember { mutableStateOf("Click the button for a diabetes tip!") }

        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = Color(185, 220, 249, 255)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.drop) ,
                contentDescription = "",
                Modifier
                    .height(300.dp)
                    .background(color = Color(185,220,249,255))
                ,
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = "Random Advice",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 28.sp

            )
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            Text(
                modifier = modifier.padding(horizontal = 16.dp),
                text = currentAdvice,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                lineHeight = 40.sp
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Button(
                onClick = {
                // Update current advice with a random advice from the list
                    advicesRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            // Get all advices as a list
                            val firebaseAdvices = snapshot.children.map { it.getValue(String::class.java) }
                            // Display a random advice from the list
                            currentAdvice = firebaseAdvices.random() ?: "No advice available"
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Handle error
                        }
                    })
            },
                colors = ButtonDefaults.buttonColors(Color(80, 180, 166, 255))
            ) {
                Text(text = "Get Advice")
            }
        }
}



@Preview
@Composable
fun AdviserPreview(){
    Adviser(navController = rememberNavController())
}