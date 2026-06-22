package com.legalai.app.data.local.entity

enum class Role {
    USER,
    ASSISTANT,
    SYSTEM;

    val normalized: String get() = name.lowercase()
}