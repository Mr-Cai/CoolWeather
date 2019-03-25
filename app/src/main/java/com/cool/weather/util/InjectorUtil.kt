package com.cool.weather.util

import com.cool.weather.data.PlaceRepository
import com.cool.weather.data.WeatherRepository
import com.cool.weather.data.db.CoolWeatherDatabase
import com.cool.weather.data.network.CoolWeatherNetwork
import com.cool.weather.ui.MainModelFactory
import com.cool.weather.ui.area.ChooseAreaModelFactory
import com.cool.weather.ui.weather.WeatherModelFactory

object InjectorUtil {

    private fun getPlaceRepository() =
        PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), CoolWeatherNetwork.getInstance())

    private fun getWeatherRepository() =
        WeatherRepository.getInstance(CoolWeatherDatabase.getWeatherDao(), CoolWeatherNetwork.getInstance())

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

}