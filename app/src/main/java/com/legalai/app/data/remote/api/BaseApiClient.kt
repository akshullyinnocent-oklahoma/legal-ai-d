package com.legalai.app.data.remote.api

import io.ktor.client.HttpClient

abstract class BaseApiClient(protected val client: HttpClient) {
    abstract suspend fun createChatCompletion(request: ChatRequest): ChatResponse
    abstract val provider: ApiProvider
}
