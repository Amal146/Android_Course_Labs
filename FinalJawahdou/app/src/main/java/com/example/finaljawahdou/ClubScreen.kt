package com.example.finaljawahdou

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finaljawahdou.model.DataSource
import com.example.finaljawahdou.model.Club
import com.example.finaljawahdou.ui.theme.FinalJawahdouTheme


@Composable
fun ClubCard(
    club: Club,
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
            .padding(top = 75.dp , start = 16.dp , end = 16.dp),

        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
        )    ){
        Column (
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable ( onClick =  {expanded = !expanded} ),
            horizontalAlignment = Alignment.CenterHorizontally){
            IconButton(
                onClick = {expanded = !expanded},
                modifier = modifier.height(80.dp).width(80.dp)
            ) {
                Image(
                    modifier = modifier
                        .clip(RoundedCornerShape(90.dp))
                        .wrapContentSize(Alignment.Center),
                    painter = painterResource(id = club.imgRes),
                    contentDescription = "",
                )

        }
            Text(
                text = stringResource(id = club.nameRes),
                style = MaterialTheme.typography.displayMedium,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = modifier.height(20.dp))

        if (expanded) {
            Text(
                modifier = modifier.padding(8.dp),
                text = stringResource(id = club.descripRes),
                style = MaterialTheme.typography.displaySmall,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun ClubList(clubs: List<Club> , modifier: Modifier = Modifier){
    LazyColumn {
        items(clubs){
                Club -> ClubCard(club = Club, modifier= modifier)
        }
    }
}

@Preview
@Composable
fun CardPreview() {

    FinalJawahdouTheme {
        Surface(
            color= MaterialTheme.colorScheme.background
        ) {
            ClubList(
                clubs = DataSource().loadClubs()
            )
        }
    }
}