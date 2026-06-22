package com.legalai.app.data.local.entity

import androidx.room.*
import java.util.UUID

@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String? = null,
    val instructions: String, // Full instructions content
    val parametersJson: String? = null, // JSON schema for parameters
    val tags: String = "[]", // JSON array of tags
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
}
