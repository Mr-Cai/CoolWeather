package com.cool.weather.ui

import androidx.lifecycle.ViewModel
import com.cool.weather.data.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {
    fun isWeatherCached() = repository.isWeatherCached()
}