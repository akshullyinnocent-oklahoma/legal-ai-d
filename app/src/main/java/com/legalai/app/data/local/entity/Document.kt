package com.legalai.app.data.local.entity

import androidx.room.*
import java.util.UUID

@Entity(
    tableName = "documents",
    indices = [Index(value = ["projectId"]), Index(value = ["type"])]
)
data class Document(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val projectId: Long,
    val filename: String,
    val mimeType: String,
    val rawTextEncrypted: ByteArray, // Full document text, encrypted
    val fileSize: Long = 0,
    val pageCount: Int = 0,
    val checksum: String, // SHA256 for integrity
    val type: Type = Type.LEGAL_BRIEF,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    enum class Type {
        LEGAL_BRIEF, PETITION, MEMORANDUM, STATUTE, CASE_LAW, OTHER
    }
}