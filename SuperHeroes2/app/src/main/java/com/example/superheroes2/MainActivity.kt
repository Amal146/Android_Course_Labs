package com.example.superheroes2

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
import com.example.superheroes2.model.DataSource
import com.example.superheroes2.ui.theme.SuperHeroes2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroes2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier){
    Scaffold (
        modifier = modifier ,
        topBar = { DisplayTopAppBar() }
    ){
        var data = DataSource().loadHeroes()
        HeroList(heroes = data , modifier = modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(title = {
        Text(text = "SuperHeroes" , style = MaterialTheme.typography.displayLarge)
    })
}

@Preview()
@Composable
fun GreetingPreview() {
    SuperHeroes2Theme (darkTheme = true){
        App()
    }
}