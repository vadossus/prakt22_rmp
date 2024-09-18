package com.example.pract22_weather

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var enter_city = findViewById<EditText>(R.id.enter_city)

        var button = findViewById<AppCompatButton>(R.id.button_search)

        var city = findViewById<TextView>(R.id.city_id)

        var temperature = findViewById<TextView>(R.id.temperature_id)

        var pressure = findViewById<TextView>(R.id.pressure_id)

        var speed_air = findViewById<TextView>(R.id.speed_weather_id)


        button.setOnClickListener{
            if (!enter_city.text.isNullOrEmpty())
            {
                var key = "7e3c47d7cd961be994875836fdea7b2e"
                var prompt = enter_city.text.toString()
                var url = "https://api.openweathermap.org/data/2.5/weather?q=$prompt&appid=$key&units=metric"
                val queue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(
                    com.android.volley.Request.Method.GET,
                    url,
                    {
                        responce ->
                        val obj = JSONObject(responce)
                        val main = obj.getJSONObject("main")
                        val temp = main.getString("temp")
                        val pres = main.getString("pressure")
                        val wind = obj.getJSONObject("wind").getString("speed")

                        pressure.text = "$pres"
                        city.text = prompt
                        temperature.text = "$temp"
                        speed_air.text = "$wind"
                    },
                    {
                        Log.d("Ошибка","Volley ошибка: $it")
                    }

                )
                queue.add(stringRequest)
            }
            else
            {
                val snackbar = Snackbar.make(it, "Ошибка: Введите запрос в поле ввода", Snackbar.LENGTH_SHORT)
                snackbar.setActionTextColor(Color.RED)
                snackbar.show()
            }
        }




    }
}