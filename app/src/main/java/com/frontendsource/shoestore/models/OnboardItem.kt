package com.frontendsource.shoestore.models

import androidx.annotation.DrawableRes

data class OnboardItem(
    @DrawableRes val image: Int,
    val text: String,
)
