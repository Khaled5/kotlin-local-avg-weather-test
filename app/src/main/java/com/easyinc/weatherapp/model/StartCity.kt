package com.easyinc.weatherapp.model

data class StartCity(
    var city: CityEntity? = null,
    var seasons: List<SeasonEntity> = emptyList(),
    var months: List<MonthEntity> = emptyList()
)