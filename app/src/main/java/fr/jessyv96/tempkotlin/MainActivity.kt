package fr.jessyv96.tempkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import fr.jessyv96.tempkotlin.model.WeatherResponse
import fr.jessyv96.tempkotlin.service.WeatherService
import fr.jessyv96.tempkotlin.ui.theme.TempKotlinTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (city, setCity) = remember { mutableStateOf("") }
            var temp by remember { mutableStateOf("") }

            TempKotlinTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    inputTemp(
                        onCityChange = setCity,
                        onTempChange = { temp = it.toString() }
                    )
                    tempResult(city, temp)
                }
            }
        }
    }
}
@Composable
fun inputTemp(
    onCityChange: (String) -> Unit,
    onTempChange: (Double) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val weatherService = WeatherService()
    var query by remember { mutableStateOf(TextFieldValue("")) }
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = query,
            onValueChange = {
                query = it
            },
            label = { Text(text = "City") },
            placeholder = { Text(text = "Ex: Paris") },
        )
        Button(onClick = {
            coroutineScope.launch {
                val weather: WeatherResponse = weatherService.getWeather(query.text)
                onCityChange(weather.name)
                onTempChange(weather.main.temp)
            }
        }) {
            Text(text = "Valider")
        }
    }
}


@Composable
fun tempResult(city: String, temp: String) {
    Column {
        Text(text = city)
        Text(text = temp.toString())
    }
}