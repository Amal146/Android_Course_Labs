package com.example.superheroes

import SuperheroesTheme
import android.graphics.drawable.shapes.Shape
import android.view.Display
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superheroes.model.Datasource
import com.example.superheroes.model.Hero

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
    ){
        Row (
            Modifier
                .height(72.dp)
                .padding(16.dp)
                .fillMaxWidth()
        ){
            Column(
                Modifier
                .width(224.dp)
            ){
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 8.sp
                )
            }
            Spacer(modifier = Modifier.width(48.dp))
            Box(modifier = Modifier
                .height(72.dp)
                .width(72.dp)
            ){
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = "",
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(8.dp))

                )
            }
        }

    }
}

@Composable
fun HeroesList(hereos: List<Hero> , modifier: Modifier = Modifier){
    LazyColumn (modifier = Modifier){
        items(hereos) {
                Hero -> HeroItem(hero = Hero, modifier = modifier)
        }
    }
}

@Preview()
@Composable
fun HeroApp(){
    SuperheroesTheme(darkTheme = false) {
     Surface (
         color = MaterialTheme.colorScheme.background
     ){
         HeroesList(hereos = Datasource().loadHeroes())
     }
    }
}