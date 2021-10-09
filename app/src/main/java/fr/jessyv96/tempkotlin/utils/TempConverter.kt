package fr.jessyv96.tempkotlin.utils

object TempConverter {
    fun fahrenheitFromCelsius(celsius: Double): Double {
        return ( (9.0/5.0) * celsius + 32.0)
    }

    fun celsiusFromFahrenheit(fahrenheit: Double): Double {
        return (5.0/9.0) * (fahrenheit - 32.0)
    }
}