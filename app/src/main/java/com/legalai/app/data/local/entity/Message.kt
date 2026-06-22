package com.legalai.app.data.local.entity

import androidx.room.*

@Entity(
    tableName = "messages",
    indices = [Index(value = ["projectId"]), Index(value = ["createdAt"])]
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectId: Long,
    val role: Role,
    val content: String,
    val tokensUsed: Int? = null,
    val modelUsed: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
