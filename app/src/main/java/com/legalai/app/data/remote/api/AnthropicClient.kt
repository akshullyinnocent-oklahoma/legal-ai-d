package com.legalai.app.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AnthropicClient(
    client: HttpClient,
    private val apiKey: String
) : BaseApiClient(client) {
    override val provider: ApiProvider = ApiProvider.ANTHROPIC

    override suspend fun createChatCompletion(request: ChatRequest): ChatResponse {
        val anthropicRequest = AnthropicRequest(
            model = request.model,
            maxTokens = request.maxTokens ?: 4096,
            temperature = request.temperature ?: 0.7,
            topP = request.topP,
            messages = request.messages.map { msg ->
                AnthropicMessage(
                    role = msg.role,
                    content = listOf(AnthropicContent(text = msg.content))
                )
            },
            system = request.system
        )
        
        val response: AnthropicResponse = client.post("v1/messages") {
            headers {
                append("x-api-key", apiKey)
                append("anthropic-version", "2023-06-01")
            }
            setBody(anthropicRequest)
            contentType(ContentType.Application.Json)
        }.body()
        
        return ChatResponse(
            id = response.id,
            content = response.content.firstOrNull()?.text ?: "",
            model = response.model,
            usage = Usage(
                promptTokens = response.usage.inputTokens,
                completionTokens = response.usage.outputTokens,
                totalTokens = response.usage.inputTokens + response.usage.outputTokens
            )
        )
    }
}

@Serializable
data class AnthropicRequest(
    @SerialName("model") val model: String,
    @SerialName("max_tokens") val maxTokens: Int = 4096,
    @SerialName("temperature") val temperature: Double = 0.7,
    @SerialName("top_p") val topP: Double? = null,
    @SerialName("messages") val messages: List<AnthropicMessage>,
    @SerialName("system") val system: String? = null
)

@Serializable
data class AnthropicMessage(
    @SerialName("role") val role: String,
    @SerialName("content") val content: List<AnthropicContent>
)

@Serializable
data class AnthropicContent(
    @SerialName("type") val type: String = "text",
    @SerialName("text") val text: String
)

@Serializable
data class AnthropicResponse(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("role") val role: String,
    @SerialName("content") val content: List<AnthropicContent>,
    @SerialName("model") val model: String,
    @SerialName("stop_reason") val stopReason: String?,
    @SerialName("usage") val usage: AnthropicUsage
)

@Serializable
data class AnthropicUsage(
    @SerialName("input_tokens") val inputTokens: Int,
    @SerialName("output_tokens") val outputTokens: Int
) {
}
