package fr.jessyv96.tempkotlin.plugins
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

object KtorClient {
    val client = HttpClient(CIO) {
        install(JsonFeature)
    }
}