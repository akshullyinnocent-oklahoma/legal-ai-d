package com.legalai.app.data.local.entity

import androidx.room.*

@Entity(tableName = "api_configs")
data class ApiConfig(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val providerName: String,
    val apiKeyEncrypted: String,
    val baseUrl: String? = null,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
}
