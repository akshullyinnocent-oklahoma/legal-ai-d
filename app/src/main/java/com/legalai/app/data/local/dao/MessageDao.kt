package com.legalai.app.data.local.dao

import androidx.room.*
import com.legalai.app.data.local.entity.Message
import com.legalai.app.data.local.entity.Role
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages WHERE projectId = :projectId ORDER BY createdAt ASC")
    fun getMessagesByProject(projectId: Long): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getMessageById(id: String): Message?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)

    @Update
    suspend fun update(message: Message)

    @Delete
    suspend fun delete(message: Message)

    @Query("DELETE FROM messages WHERE projectId = :projectId")
    suspend fun deleteAllForProject(projectId: Long)

    @Query("SELECT * FROM messages WHERE projectId = :projectId ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getRecentMessages(projectId: Long, limit: Int = 50): List<Message>
}