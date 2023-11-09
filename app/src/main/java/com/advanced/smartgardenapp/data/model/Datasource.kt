package com.advanced.smartgardenapp.data.model

import com.advanced.smartgardenapp.R

/***
 * Created by HoangRyan aka LilDua on 11/8/2023.
 */
class Datasource {
    fun loadGarden(): List<Garden> {
        return listOf(
            Garden("1001","Anh Duc Garden", R.drawable.image_garden_1),
            Garden("1002","Huu Thom Garden", R.drawable.image_garden_1),
            Garden("1003","Thanh Loc Garden", R.drawable.image_garden_1),
        )
    }

    fun loadPlants(): List<Plant> {
        return listOf(
            Plant("1001","Basil","Planted 8 days ago",R.drawable.image_plant_1),
            Plant("1002","Mint","Planted 5 days ago",R.drawable.image_plant_2),
            Plant("1003","Lemon Balm","Planted yesterday",R.drawable.image_plant_3),
            Plant("1004","Oregano","Planted today",R.drawable.image_plant_4),
        )
    }
}