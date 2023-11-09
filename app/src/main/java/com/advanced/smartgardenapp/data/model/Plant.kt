package com.advanced.smartgardenapp.data.model

import androidx.annotation.DrawableRes

/***
 * Created by HoangRyan aka LilDua on 11/9/2023.
 */
class Plant(
    val id: String,
    val name: String,
    val dayPlant: String,
    @DrawableRes val image: Int
)