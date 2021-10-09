package fr.jessyv96.tempkotlin.service

import fr.jessyv96.tempkotlin.model.WeatherResponse
import fr.jessyv96.tempkotlin.plugins.KtorClient
import fr.jessyv96.tempkotlin.utils.Constants
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class WeatherService {
    suspend fun getWeather(city: String): WeatherResponse {
       val response: HttpResponse = KtorClient.client.get("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=${Constants.OPEN_WEATHER_API_KEY}") {
         contentType(ContentType.Application.Json)
       }

      return response.receive()
    }
}