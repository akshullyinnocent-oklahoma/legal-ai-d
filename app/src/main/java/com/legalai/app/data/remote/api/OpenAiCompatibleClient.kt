package com.legalai.app.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class OpenAiCompatibleClient(
    client: HttpClient,
    private val apiKey: String,
    private val providerName: String
) : BaseApiClient(client) {
    override val provider: ApiProvider = ApiProvider.fromName(providerName)

    override suspend fun createChatCompletion(request: ChatRequest): ChatResponse {
        val response: OpenAiResponse = client.post("chat/completions") {
            bearerAuth(apiKey)
            setBody(request)
            contentType(ContentType.Application.Json)
        }.body()
        
        val choice = response.choices?.firstOrNull()
        return ChatResponse(
            id = response.id,
            content = choice?.message?.content,
            model = response.model,
            usage = choice?.usage
        )
    }
}

@Serializable
data class OpenAiResponse(
    val id: String?,
    val choices: List<Choice>?,
    val model: String?
) {
    @Serializable
    data class Choice(
        val message: ChatMessage?,
        val finishReason: String?,
        val index: Int?,
        val usage: Usage?
    )
}
