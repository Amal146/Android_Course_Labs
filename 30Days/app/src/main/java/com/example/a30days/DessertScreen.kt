package com.example.a30days

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30days.model.DataSource
import com.example.a30days.model.Dessert
import com.example.a30days.ui.theme.DaysTheme


@Composable
fun DessertCard(
    dessert: Dessert,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)

        ,
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
        )    ){
        Column (
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Text(
                text = stringResource(id = dessert.dayNb),
                style = MaterialTheme.typography.displaySmall,

            )
            Text(
                text = stringResource(id = dessert.nameRes),
                style = MaterialTheme.typography.displayMedium
            )
            }
            IconButton(
                onClick = {expanded = !expanded} ,
                Modifier.fillMaxWidth(3f).height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = dessert.imgRes),
                    contentDescription = "",
                )
            }

            if (expanded) {
                Text(
                    text = stringResource(id = dessert.descripRes),
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 36.sp
                )
            }
        }
    }


@Composable
fun DessertList(desserts: List<Dessert> , modifier: Modifier = Modifier){
    LazyColumn {
        items(desserts){
            Dessert -> DessertCard(dessert = Dessert, modifier= modifier)
        }
    }
}
@Preview
@Composable
fun CardPreview() {
    DaysTheme {
        Surface(
            color= MaterialTheme.colorScheme.background
        ) {
            DessertList(
                desserts = DataSource().loadDesserts()
            )
        }
    }
}