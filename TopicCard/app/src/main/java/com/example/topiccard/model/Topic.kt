package com.example.topiccard.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
        @StringRes val stringResourceId: Int,
        val courseNb: Int,
        @DrawableRes val imageResourceId: Int
)