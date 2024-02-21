package com.example.lemonade

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults.containerColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun makingLemonade(modifier: Modifier = Modifier){
    var state by remember { mutableStateOf( R.string.lemon_tree) }

    val image = when (state) {
        R.string.lemon_tree -> R.drawable.lemon_tree
        R.string.lemon -> R.drawable.lemon_squeeze
        R.string.glass_of_lemonade -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val instruction = when (state) {
        R.string.lemon_tree -> R.string.to_lemon
        R.string.lemon -> R.string.squeeze
        R.string.glass_of_lemonade -> R.string.drink
        else -> R.string.empty
    }
    val next = when(state){
        R.string.lemon_tree -> R.string.lemon
        R.string.lemon -> R.string.glass_of_lemonade
        R.string.glass_of_lemonade -> R.string.empty_glass
        else -> R.string.lemon_tree
    }
    Column (
        modifier = Modifier
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Lemonade" ,
            fontSize = 18.sp ,
            modifier = Modifier
                .background(Color(255, 255, 51))
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp),
            textAlign = TextAlign.Center ,
                        )

        SmallFloatingActionButton(
            onClick = {state = next} ,
            modifier = Modifier
                .height(600.dp)
                .width(250.dp)
                .padding(bottom = 100.dp)
                .padding(top = 150.dp),
            containerColor = Color(153,204,255)

        ){
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(id = R.string.lemon_tree))
        }

        Text(
            text = stringResource(instruction) ,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(120.dp) ,
            textAlign = TextAlign.Center ,
            )
    }
}


@Preview
@Composable
fun LemonadeApp(){
    makingLemonade()
}