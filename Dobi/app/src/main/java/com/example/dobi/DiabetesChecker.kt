package com.example.dobi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checker(navController: NavHostController, modifier: Modifier = Modifier.background(Color(
    254,
    242,
    172,
    255
)
)){
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
                    Text(text = "Blood Sugar Level Checker 📈🩸" , textAlign = TextAlign.Center)
                }
                ,

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(33, 148, 241, 255),
                    titleContentColor = Color(255,255,255) ,
                )

            )
        }
    ) {
        CheckerScreen(modifier = modifier.padding(it))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckerScreen(modifier: Modifier = Modifier) {
    var age by remember { mutableStateOf(0f) }
    var bloodGlucose by remember { mutableStateOf(0.0f) }
    var selectedTime by remember { mutableStateOf("Fasting") }
    var result by remember { mutableStateOf("") }
    val timeOptions = listOf("Fasting", "Post-meal", "2hrs Post-meal")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color(254, 242, 172, 255)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Age: ${age.toInt()}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color(139, 188, 62, 255)
        )
        Slider(
            modifier = modifier.padding(horizontal = 16.dp),
            value = age,
            onValueChange = { age = it },
            valueRange = 0f..100f,
            steps = 99
        )



        Text(
            text = "Glucose Level(mmol/L): ${String.format("%.1f", bloodGlucose)}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color(239, 98, 145, 255)
        )
        Slider(
            modifier = modifier.padding(horizontal = 16.dp),
            value = bloodGlucose,
            onValueChange = { bloodGlucose = it },
            valueRange = 1.0f..12.0f,
            steps = 110
        )

        Text(
            text = "Time:",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color(104, 182, 242, 255)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedTime,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().padding(top = 24.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                timeOptions.forEach { option ->
                    DropdownMenuItem(onClick = { selectedTime = option }, text = { Text(option) })

                }
            }
        }


        Button(
            modifier = modifier.width(150.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(33, 149, 242, 255)),
            onClick = {
            result = getBloodGlucoseResult(age.toInt(), selectedTime, bloodGlucose)
        }) {
            Text(text = "Check")
        }

        Text(
            text = result,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            color = when (result) {
            "Normal" -> Color.Green
            "Almost Severe" -> Color.Magenta
            "Severe" -> Color.Red
            else -> Color.Black
        })
    }
}

fun getBloodGlucoseResult(age: Int, time: String, bloodGlucose: Float): String {
    return when (time) {
        "Fasting" -> when {
            (age <= 18 && bloodGlucose in 3.9..5.8) ||
                    (age in 19..64 && bloodGlucose in 4.0..5.4) ||
                    (age >= 65 && bloodGlucose in 4.5..6.0) -> "Normal"
            bloodGlucose > 6.9 -> "Severe"
            bloodGlucose > 5.9 -> "Almost Severe"
            else -> "Severe"
        }
        "Post-meal" -> when {
            bloodGlucose <= 7.8 -> "Normal"
            bloodGlucose > 10.0 -> "Severe"
            bloodGlucose > 7.9 -> "Almost Severe"
            else -> "Unknown"
        }
        "2hrs Post-meal" -> when {
            bloodGlucose in 5.0..7.0 -> "Normal"
            bloodGlucose > 8.0 -> "Severe"
            bloodGlucose > 7.1 -> "Almost Severe"
            else -> "Severe"
        }
        else -> "Severe"
    }
}




@Preview
@Composable
fun CheckerPreview() {
    Checker(navController = rememberNavController())
}