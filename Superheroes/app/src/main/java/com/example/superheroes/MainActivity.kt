@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import SuperheroesTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.example.superheroes.model.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DisplayTopBar()
        }
    ) {
        val hereos = Datasource().loadHeroes()
        HeroesList(hereos = hereos, modifier = Modifier.padding(it))
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroPreview() {
    SuperheroesTheme (darkTheme = false){
        SuperheroesApp()
    }
}
@Preview(showBackground = true)
@Composable
fun SuperHeroPreviewDark() {
    SuperheroesTheme (darkTheme = true){
        SuperheroesApp()
    }
}

//display the top app bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = { Text(
            text = "Superheroes",
            style = MaterialTheme.typography.displayLarge
        )
    }
    )
}