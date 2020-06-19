package com.easyinc.weatherapp.model

data class FinalCity(
    var city: CityEntity? = null,
    var season: SeasonEntity? = null,
    var months: List<MonthEntity> = emptyList()
)