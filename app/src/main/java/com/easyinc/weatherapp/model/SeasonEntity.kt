package com.easyinc.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season")
data class SeasonEntity(
    val name: String,
    var cityId: Long = 0
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}