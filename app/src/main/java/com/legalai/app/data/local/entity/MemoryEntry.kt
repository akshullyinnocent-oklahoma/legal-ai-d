package com.legalai.app.data.local.entity

import androidx.room.*
import java.util.UUID

@Entity(
    tableName = "memory_entries",
    indices = [Index(value = ["projectId", "type"])]
)
data class MemoryEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectId: Long? = null,
    val type: MemoryType,
    val content: String,
    val context: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class MemoryType {
    SYSTEM,
    USER,
    ASSISTANT,
    TASK_LOG
}
