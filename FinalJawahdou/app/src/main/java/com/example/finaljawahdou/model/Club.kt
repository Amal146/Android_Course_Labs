package com.example.finaljawahdou.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Club(
    @DrawableRes val imgRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descripRes: Int
)
