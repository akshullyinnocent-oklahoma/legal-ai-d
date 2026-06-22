package com.legalai.app.data.remote.api

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClientFactory {
    fun create(provider: ApiProvider, apiKey: String, baseUrl: String?): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.INFO
                logger = Logger.SIMPLE
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 120_000
                connectTimeoutMillis = 30_000
            }
            defaultRequest {
                header("Authorization", "Bearer $apiKey")
                header("Content-Type", "application/json")
                url(baseUrl ?: provider.defaultBaseUrl)
            }
        }
    }
}