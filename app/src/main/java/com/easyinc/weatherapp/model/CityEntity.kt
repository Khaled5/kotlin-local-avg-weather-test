package com.easyinc.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    val name: String,
    val type: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}