package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30days.model.DataSource
import com.example.a30days.model.Dessert
import com.example.a30days.ui.theme.DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun DessertsApp(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DessertTopAppBar()
        }
    ) {
        DessertList(
            desserts = DataSource().loadDesserts(),
            modifier = Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = { Text(
            text = "30 Days of Desserts",
            style = MaterialTheme.typography.displayLarge
        )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DessertsPreview() {
    DaysTheme (darkTheme = false){
        DessertsApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DessertsPreviewDark() {
    DaysTheme (darkTheme = true){
        DessertsApp()
    }
}