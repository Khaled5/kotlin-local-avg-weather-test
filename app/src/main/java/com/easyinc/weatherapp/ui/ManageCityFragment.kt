package com.easyinc.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.easyinc.weatherapp.R
import com.easyinc.weatherapp.model.CityEntity
import com.easyinc.weatherapp.model.MonthEntity
import com.easyinc.weatherapp.model.SeasonEntity
import com.easyinc.weatherapp.model.StartCity
import com.easyinc.weatherapp.common.base.BaseFragment
import com.easyinc.weatherapp.common.extentions.isFieldEmpty
import com.easyinc.weatherapp.common.extentions.menuAdapter
import com.easyinc.weatherapp.common.util.Resource
import kotlinx.android.synthetic.main.autumn_season.*
import kotlinx.android.synthetic.main.fragment_manage_city.*
import kotlinx.android.synthetic.main.spring_season.*
import kotlinx.android.synthetic.main.summer_season.*
import kotlinx.android.synthetic.main.winter_season.*

class ManageCityFragment : BaseFragment() {

    private lateinit var startCity: StartCity
    private var cityNameArgument: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cityNameArgument = requireArguments().getString(resources.getString(R.string.city_name_argument))

        mViewModel = (activity as MainActivity).mainViewModel

        mainActivity = (activity as MainActivity)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_manage_city
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        drawStartData()

        if (cityNameArgument != null)
            getAndObserveCityToUpdate(cityNameArgument!!)


        send_city.setOnClickListener {
            isDataValid()
        }
    }

    private fun handleSave(){
        when (cityNameArgument) {
            null -> mViewModel.addCity(getStartCity())
            else -> mViewModel.updateCity(convertDataWithIds(startCity))
        }
        mViewModel.getCitiesName()
        navController.navigate(R.id.action_manageCityFragment_to_weatherAvgFragment)
    }

    private fun isDataValid(){
        val editTextList = listOf(city_et,january_feh_et, january_cel_et, january_kel_et, february_feh_et, february_cel_et, february_kel_et,
        march_feh_et, march_cel_et, march_kel_et, april_feh_et, april_cel_et, april_kel_et, may_feh_et, may_cel_et, may_kel_et, june_feh_et, june_cel_et, june_kel_et,
        jule_feh_et, jule_cel_et, jule_kel_et, august_feh_et, august_cel_et, august_kel_et, september_feh_et, september_cel_et, september_kel_et, october_feh_et,
        october_cel_et, october_kel_et, november_feh_et, november_cel_et, november_kel_et, december_feh_et, december_cel_et, december_kel_et)

        val isCityTypeEmpty = city_type_menu.isFieldEmpty()

        val hasEmpty = editTextList.map { it.isFieldEmpty() }
        if (hasEmpty.contains(true) || isCityTypeEmpty)
            mainActivity.showSnackbar(resources.getString(R.string.all_fields_are_required))
        else
            handleSave()
    }

    private fun getAndObserveCityToUpdate(cityName: String){
        mViewModel.getCityToUpdate(cityName)
        mViewModel.observeCityToUpdate().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    startCity = it.data!!
                    drawData(it.data)
                }
                Resource.Status.ERROR -> {}
            }
        })
    }

    private fun drawStartData(){
        city_type_menu.setAdapter(resources.getStringArray(R.array.city_type).menuAdapter(requireContext()))
    }

    private fun drawData(startCity: StartCity){
        city_et.setText(startCity.city?.name)
        drawMonths(startCity.months)
    }

    private fun convertDataWithIds(startCity: StartCity): StartCity{

        val newStartCity = getStartCity()
        newStartCity.city!!.id = startCity.city!!.id

        var seasonCounter = 0
        newStartCity.seasons.map {
            it.id = startCity.seasons[seasonCounter].id
            it.cityId = startCity.seasons[seasonCounter].cityId
            seasonCounter++
            it
        }

        var monthsCounter = 0
        newStartCity.months.map {
            it.id = startCity.months[monthsCounter].id
            it.seasonId = startCity.months[monthsCounter].seasonId
            monthsCounter++
            it
        }

        return newStartCity
    }

    private fun drawMonths(months: List<MonthEntity>) {
        //January
        january_feh_et.setText(months[1].fahrenheitTemp.toString())
        january_cel_et.setText(months[1].celsiusTemp.toString())
        january_kel_et.setText(months[1].kelvinTemp.toString())

        //February
        february_feh_et.setText(months[2].fahrenheitTemp.toString())
        february_cel_et.setText(months[2].celsiusTemp.toString())
        february_kel_et.setText(months[2].kelvinTemp.toString())

        //March
        march_feh_et.setText(months[3].fahrenheitTemp.toString())
        march_cel_et.setText(months[3].celsiusTemp.toString())
        march_kel_et.setText(months[3].kelvinTemp.toString())

        //April
        april_feh_et.setText(months[4].fahrenheitTemp.toString())
        april_cel_et.setText(months[4].celsiusTemp.toString())
        april_kel_et.setText(months[4].kelvinTemp.toString())

        //May
        may_feh_et.setText(months[5].fahrenheitTemp.toString())
        may_cel_et.setText(months[5].celsiusTemp.toString())
        may_kel_et.setText(months[5].kelvinTemp.toString())

        //June
        june_feh_et.setText(months[6].fahrenheitTemp.toString())
        june_cel_et.setText(months[6].celsiusTemp.toString())
        june_kel_et.setText(months[6].kelvinTemp.toString())

        //July
        jule_feh_et.setText(months[7].fahrenheitTemp.toString())
        jule_cel_et.setText(months[7].celsiusTemp.toString())
        jule_kel_et.setText(months[7].kelvinTemp.toString())

        //August
        august_feh_et.setText(months[8].fahrenheitTemp.toString())
        august_cel_et.setText(months[8].celsiusTemp.toString())
        august_kel_et.setText(months[8].kelvinTemp.toString())

        //September
        september_feh_et.setText(months[9].fahrenheitTemp.toString())
        september_cel_et.setText(months[9].celsiusTemp.toString())
        september_kel_et.setText(months[9].kelvinTemp.toString())

        //October
        october_feh_et.setText(months[10].fahrenheitTemp.toString())
        october_cel_et.setText(months[10].celsiusTemp.toString())
        october_kel_et.setText(months[10].kelvinTemp.toString())

        //November
        november_feh_et.setText(months[11].fahrenheitTemp.toString())
        november_cel_et.setText(months[11].celsiusTemp.toString())
        november_kel_et.setText(months[11].kelvinTemp.toString())

        //December
        december_feh_et.setText(months[0].fahrenheitTemp.toString())
        december_cel_et.setText(months[0].celsiusTemp.toString())
        december_kel_et.setText(months[0].kelvinTemp.toString())

    }

    //________________________SEND____________________________________//

    //StartCityEntity
    private fun getStartCity(): StartCity{
        val monthsList = listOf(getDecData(),getJanData(),getFebData(),
                                                    getMarData(),getAprData(),getMayData(),
                                                    getJuneData(),getJulyData(),getAugData(),
                                                    getSepData(),getOctData(),getNovData())
        val seasonsList = listOf(getWinterSeason(),getSpringSeason(),getSummerSeason(),getAutumnSeason())

        return StartCity(getCityData(),seasonsList,monthsList)
    }

    //City
    private fun getCityData(): CityEntity{
        return CityEntity(city_et.text.toString(),city_type_menu.text.toString())
    }

    //Seasons
    private fun getSummerSeason(): SeasonEntity{
        return SeasonEntity(resources.getString(R.string.summer))
    }
    private fun getSpringSeason(): SeasonEntity{
        return SeasonEntity(resources.getString(R.string.spring))
    }
    private fun getWinterSeason(): SeasonEntity{
        return SeasonEntity(resources.getString(R.string.winter))
    }
    private fun getAutumnSeason(): SeasonEntity{
        return SeasonEntity(resources.getString(R.string.autumn))
    }

    //Months
    private fun getJanData(): MonthEntity{
        val feh = january_feh_et.text.toString()
        val cel = january_cel_et.text.toString()
        val kel = january_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.jan),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getFebData(): MonthEntity{
        val feh = february_feh_et.text.toString()
        val cel = february_cel_et.text.toString()
        val kel = february_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.feb),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getMarData(): MonthEntity{
        val feh = march_feh_et.text.toString()
        val cel = march_cel_et.text.toString()
        val kel = march_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.mar),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getAprData(): MonthEntity{
        val feh = april_feh_et.text.toString()
        val cel = april_cel_et.text.toString()
        val kel = april_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.april),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getMayData(): MonthEntity{
        val feh = may_feh_et.text.toString()
        val cel = may_cel_et.text.toString()
        val kel = may_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.may),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getJuneData(): MonthEntity{
        val feh = june_feh_et.text.toString()
        val cel = june_cel_et.text.toString()
        val kel = june_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.june),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getJulyData(): MonthEntity{
        val feh = jule_feh_et.text.toString()
        val cel = jule_cel_et.text.toString()
        val kel = jule_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.jule),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getAugData(): MonthEntity{
        val feh = august_feh_et.text.toString()
        val cel = august_cel_et.text.toString()
        val kel = august_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.aug),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getSepData(): MonthEntity{
        val feh = september_feh_et.text.toString()
        val cel = september_cel_et.text.toString()
        val kel = september_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.sep),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getOctData(): MonthEntity{
        val feh = october_feh_et.text.toString()
        val cel = october_cel_et.text.toString()
        val kel = october_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.oct),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getNovData(): MonthEntity{
        val feh = november_feh_et.text.toString()
        val cel = november_cel_et.text.toString()
        val kel = november_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.nov),cel.toInt(),feh.toInt(),kel.toInt())
    }

    private fun getDecData(): MonthEntity{
        val feh = december_feh_et.text.toString()
        val cel = december_cel_et.text.toString()
        val kel = december_kel_et.text.toString()

        return MonthEntity(resources.getString(R.string.dec),cel.toInt(),feh.toInt(),kel.toInt())
    }

}