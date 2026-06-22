package com.legalai.app.data.local.dao

import androidx.room.*
import com.legalai.app.data.local.entity.MemoryEntry
import com.legalai.app.data.local.entity.MemoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memory_entries WHERE projectId = :projectId ORDER BY updatedAt DESC LIMIT :limit")
    suspend fun getRecentMemory(projectId: Long, limit: Int = 50): List<MemoryEntry>

    @Query("SELECT * FROM memory_entries ORDER BY updatedAt DESC LIMIT :limit")
    suspend fun getGlobalMemory(limit: Int = 50): List<MemoryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MemoryEntry)

    @Update
    suspend fun update(entry: MemoryEntry)

    @Delete
    suspend fun delete(entry: MemoryEntry)
}