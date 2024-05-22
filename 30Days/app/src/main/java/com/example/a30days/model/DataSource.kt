package com.example.a30days.model

import com.example.a30days.R

class DataSource () {
    fun loadDesserts(): List<Dessert> {
        return listOf<Dessert>(
            Dessert(
                dayNb = R.string.day1,
                imgRes = R.drawable.brownies,
                nameRes = R.string.dessert1,
                descripRes = R.string.description1
            ),
            Dessert(
                dayNb = R.string.day2,
                imgRes = R.drawable.cake,
                nameRes = R.string.dessert2,
                descripRes = R.string.description2
            ),
            Dessert(
                dayNb = R.string.day3,
                imgRes = R.drawable.cheescake,
                nameRes = R.string.dessert3,
                descripRes = R.string.description3
            ),
            Dessert(
                dayNb = R.string.day4,
                imgRes = R.drawable.cupcakes,
                nameRes = R.string.dessert4,
                descripRes = R.string.description4
            ),
            Dessert(
                dayNb = R.string.day5,
                imgRes = R.drawable.lemonade,
                nameRes = R.string.drink,
                descripRes = R.string.description5
            ),
            Dessert(
                dayNb = R.string.day6,
                imgRes = R.drawable.lemontarte,
                nameRes = R.string.dessert6,
                descripRes = R.string.description6
            ),
            Dessert(
                dayNb = R.string.day7,
                imgRes = R.drawable.macarons,
                nameRes = R.string.dessert7,
                descripRes = R.string.description7
            ),
            Dessert(
                dayNb = R.string.day8,
                imgRes = R.drawable.waffles,
                nameRes = R.string.dessert8,
                descripRes = R.string.description8
            )
        )

    }
}