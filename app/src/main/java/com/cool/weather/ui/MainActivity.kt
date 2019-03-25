package com.cool.weather.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.cool.weather.R
import com.cool.weather.ui.area.ChooseAreaFragment
import com.cool.weather.ui.weather.WeatherActivity
import com.cool.weather.util.InjectorUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel =
            ViewModelProviders.of(this, InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)
        when {
            viewModel.isWeatherCached() -> {
                startActivity(Intent(this, WeatherActivity::class.java))
                finish()
            }
            else -> supportFragmentManager.beginTransaction().replace(R.id.container, ChooseAreaFragment()).commit()
        }
    }

    companion object {
        const val KEY = "208791b564ac4d55b285f4b850417daa"
    }
}
