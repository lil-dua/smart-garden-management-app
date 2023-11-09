package com.advanced.smartgardenapp.data.model

import androidx.annotation.DrawableRes

/***
 * Created by HoangRyan aka LilDua on 11/8/2023.
 */
data class Garden(
    val id: String,
    val name: String,
    @DrawableRes val image: Int
)
