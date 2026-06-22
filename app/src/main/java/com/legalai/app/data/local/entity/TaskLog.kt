package com.legalai.app.data.local.entity

import androidx.room.*

@Entity(
    tableName = "task_log",
    indices = [Index(value = ["projectId"])]
)
data class TaskLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectId: Long? = null,
    val taskType: String,
    val taskDescription: String,
    val contextReferences: String? = null,
    val outcome: String? = null,
    val importanceScore: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)
