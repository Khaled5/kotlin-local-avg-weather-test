package com.easyinc.weatherapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Observer
import com.easyinc.weatherapp.R
import com.easyinc.weatherapp.model.FinalCity
import com.easyinc.weatherapp.model.MonthEntity
import com.easyinc.weatherapp.common.base.BaseFragment
import com.easyinc.weatherapp.common.extentions.isFieldEmpty
import com.easyinc.weatherapp.common.extentions.menuAdapter
import com.easyinc.weatherapp.common.util.Resource
import kotlinx.android.synthetic.main.fragment_weather_avg.*


class WeatherAvgFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = (activity as MainActivity).mainViewModel
        mViewModel.getCitiesName()
        mainActivity = (activity as MainActivity)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_weather_avg
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCitiesNames()

        getAverage()

        observeCityDetails()
    }

    private fun drawStartData(citiesNames: List<String>){
        val listToArray = citiesNames.toTypedArray()
        city_menu.setAdapter(listToArray.menuAdapter(requireContext()))
        city_season_menu.setAdapter(resources.getStringArray(R.array.season).menuAdapter(requireContext()))
        city_temp_menu.setAdapter(resources.getStringArray(R.array.temp_types).menuAdapter(requireContext()))
    }

    private fun getAverage(){
        get_average.setOnClickListener {
            isDataValid()
        }
    }

    private fun isDataValid(){
        if (city_menu.isFieldEmpty() || city_season_menu.isFieldEmpty() || city_temp_menu.isFieldEmpty())
            mainActivity.showSnackbar(resources.getString(R.string.all_fields_are_required))
        else
            mViewModel.getCityDetails(city_menu.text.toString(),city_season_menu.text.toString())
    }

    private fun observeCitiesNames(){
        mViewModel.observeCitiesNames().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    drawStartData(it.data!!)
                }
                Resource.Status.ERROR -> {}
            }
        })
    }

    private fun observeCityDetails(){
       mViewModel.observeCityDetails().observe(viewLifecycleOwner, Observer {
           when(it.status){
               Resource.Status.SUCCESS -> {
                   drawResult(it.data!!)}
               Resource.Status.ERROR -> {}
           }
       })
    }

    private fun drawResult(finalCity: FinalCity){
        val cityType = finalCity.city?.type
        val averageTemp = tempType(city_temp_menu.text.toString(),finalCity.months).average()
        city_type.text = cityType
        avg_temp.text = averageTemp.toString()
        mainActivity.showSnackbar("${resources.getString(R.string.average_temperature)}: ${averageTemp.toString()}")

    }

    private fun tempType(temp: String, months: List<MonthEntity>): List<Int>{
        return when {
                temp.equals(resources.getString(R.string.celsius)) -> months.map { it.celsiusTemp }
                temp.equals(resources.getString(R.string.kelvin)) -> months.map { it.kelvinTemp }
                else -> months.map { it.fahrenheitTemp }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}