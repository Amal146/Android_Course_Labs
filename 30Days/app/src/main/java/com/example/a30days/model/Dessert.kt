package com.example.a30days.model

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

data class Dessert(
    @StringRes val dayNb: Int,
    @DrawableRes val imgRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descripRes: Int
)