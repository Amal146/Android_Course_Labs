package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp(){
    ArticleCard(
        background = painterResource(R.drawable.bg_compose_background),
        title = stringResource(R.string.title),
        part1 = stringResource(R.string.part1),
        part2 = stringResource(R.string.part2),
    )
}

@Composable
fun ArticleCard(background: Painter, title: String, part1: String, part2: String , modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(painter = background, contentDescription = null )

        
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = part1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign= TextAlign.Justify
        )
        Text(
            text = part2,
            modifier = Modifier.padding(16.dp),
            textAlign= TextAlign.Justify
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview(){
    MyApplicationTheme {
        ComposeArticleApp()
    }
}

