package com.example.topiccard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topiccard.data.Datasource
import com.example.topiccard.model.Topic
import com.example.topiccard.ui.theme.TopicCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopicCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicApp(){
    TopicList(topicList = Datasource().loadTopics())
}

@Composable
fun TopicList (topicList: List<Topic> , modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),

        ) {
        items(topicList) {
                Topic -> TopicCard(topic = Topic , modifier = Modifier)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier =Modifier  ){
    Card(
        modifier = modifier.width(180.dp).padding(8.dp) ,
        colors = CardDefaults.cardColors(containerColor =  Color(253, 185, 200 ))){
        Row(modifier = modifier) {
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 16.dp , end = 16.dp , top = 16.dp ),
                    fontSize = 12.sp
                )
                Row (modifier = Modifier, verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp, top = 8.dp),

                    )
                    Text (
                        text = topic.courseNb.toString(),
                        color = Color.DarkGray,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 12.sp,

                    )


                }
            }
        }
    }
}

@Preview(showBackground = true )
@Composable
private fun GreetingPreview() {
        TopicApp()
}
