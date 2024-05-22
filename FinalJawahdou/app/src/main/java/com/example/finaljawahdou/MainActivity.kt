package com.example.finaljawahdou

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finaljawahdou.model.DataSource
import com.example.finaljawahdou.ui.theme.FinalJawahdouTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalJawahdouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClubApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = modifier.padding(4.dp),
            text = "Tunis Business School Clubs",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 24.sp
        )
    })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClubApp(modifier : Modifier = Modifier){
    Scaffold (
        modifier= Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ){
        ClubList(
            clubs = DataSource().loadClubs(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FinalJawahdouTheme (darkTheme = false){
        ClubApp()
    }
}


@Preview(showBackground = true)
@Composable
fun DarkAppPreview() {
    FinalJawahdouTheme (darkTheme = true){
        ClubApp()
    }
}