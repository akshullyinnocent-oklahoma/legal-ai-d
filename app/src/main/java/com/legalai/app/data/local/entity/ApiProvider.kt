package com.legalai.app.data.local.entity

enum class ApiProvider(val defaultBaseUrl: String?) {
    OPENAI("https://api.openai.com/v1"),
    ANTHROPIC("https://api.anthropic.com"),
    OPENROUTER("https://openrouter.ai/api/v1"),
    GROQ("https://api.groq.com/openai/v1"),
    CEREBRAS("https://api.cerebras.ai/v1"),
    NVIDIA("https://api.nvidia.com/v1"),
    DEEPSEEK("https://api.deepseek.com/v1"),
    CUSTOM(null);

    companion object {
        fun fromName(name: String): ApiProvider = when (name.uppercase()) {
            "OPENAI" -> OPENAI
            "ANTHROPIC" -> ANTHROPIC
            "OPENROUTER" -> OPENROUTER
            "GROQ" -> GROQ
            "CEREBRAS" -> CEREBRAS
            "NVIDIA" -> NVIDIA
            "DEEPSEEK" -> DEEPSEEK
            else -> CUSTOM
        }
    }
}