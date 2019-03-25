package com.cool.weather.data

import com.cool.weather.data.db.WeatherDao
import com.cool.weather.data.network.CoolWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository private constructor(
    private val weatherDao: WeatherDao,
    private val network: CoolWeatherNetwork
) {

    suspend fun getWeather(weatherId: String, key: String) =
        weatherDao.getCachedWeatherInfo() ?: requestWeather(weatherId, key)

    suspend fun refreshWeather(weatherId: String, key: String) = requestWeather(weatherId, key)
    suspend fun getBingPic() = weatherDao.getCachedBingPic() ?: requestBingPic()
    suspend fun refreshBingPic() = requestBingPic()
    fun isWeatherCached() = weatherDao.getCachedWeatherInfo() != null
    fun getCachedWeather() = weatherDao.getCachedWeatherInfo()!!
    private suspend fun requestWeather(weatherId: String, key: String) = withContext(Dispatchers.IO) {
        val heWeather = network.fetchWeather(weatherId, key)
        val weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    private suspend fun requestBingPic() = withContext(Dispatchers.IO) {
        val url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

    companion object {
        private var instance: WeatherRepository? = null
        fun getInstance(weatherDao: WeatherDao, network: CoolWeatherNetwork) =
            instance ?: synchronized(WeatherRepository::class.java) {
                instance ?: WeatherRepository(weatherDao, network)
            }
    }

}