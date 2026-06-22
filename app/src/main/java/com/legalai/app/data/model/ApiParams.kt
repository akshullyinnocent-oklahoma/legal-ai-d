package com.legalai.app.data.model

data class ApiParams(
    val model: String = "gpt-4o-mini",
    val temperature: Double = 0.7,
    val topP: Double = 0.9,
    val topK: Int = 40,
    val frequencyPenalty: Double = 0.0,
    val presencePenalty: Double = 0.0,
    val maxTokens: Int = 4096
) {
}
