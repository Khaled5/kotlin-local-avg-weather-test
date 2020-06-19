package com.easyinc.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "month")
data class MonthEntity(
    val name: String,
    val celsiusTemp: Int,
    val fahrenheitTemp: Int,
    val kelvinTemp: Int,
    var seasonId: Long = 0
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}