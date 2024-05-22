package com.example.superheroes2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superheroes2.model.DataSource
import com.example.superheroes2.model.Hero

@Composable
fun HeroCard(hero: Hero, modifier : Modifier = Modifier){
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp , vertical = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(2.dp),
        )
    {
        Row (
            modifier = modifier.padding(16.dp).fillMaxWidth().height(72.dp)

        ){
            Column (
                modifier.width(257.dp).fillMaxHeight()
            ){
                Text(text = stringResource(id = hero.nameRes) , style = MaterialTheme.typography.displaySmall ,fontSize = 18.sp)
                Text(text = stringResource(id = hero.descriptionRes) , style = MaterialTheme.typography.bodyLarge , fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box (modifier = modifier.clip(RoundedCornerShape(8.dp))){
                Image(painter = painterResource(id = hero.imageRes), contentDescription = "")
            }
        }

    }
}

@Composable
fun HeroList(heroes : List<Hero> , modifier: Modifier =Modifier){
    LazyColumn(modifier = modifier) {
        items(heroes){
            Hero -> HeroCard(hero = Hero )
        }
    }
}
@Preview(device = Devices.PIXEL_3A)
@Composable
fun PreviewList(){
    var data = DataSource().loadHeroes()
    HeroList(heroes = data)
}