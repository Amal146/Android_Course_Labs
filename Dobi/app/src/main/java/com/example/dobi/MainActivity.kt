package com.example.dobi

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dobi.ui.theme.DobiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DobiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .background(color = Color(255, 255, 153))
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Welcome!",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(140,189,62)
        )
        Text(
            text = "Together to Fight Diabetes !!\n" +
                    "Doubi with you to check your \n" +
                    "Diabetes level , get advice and remind " +
                    "your medicines \n" +
                    "",
            fontFamily = FontFamily.SansSerif,
            fontSize = 25.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.drop) ,
            contentDescription = "",
            Modifier.height(250.dp)
                .background(color = Color.White) ,
        )
        


    }

}

@Preview(showBackground = true , device = Devices.PIXEL_3A , showSystemUi = true)
@Composable
fun GreetingPreview() {
    DobiTheme {
        Greeting("Android" )
    }
}