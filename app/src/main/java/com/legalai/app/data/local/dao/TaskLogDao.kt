package com.legalai.app.data.local.dao

import androidx.room.*
import com.legalai.app.data.local.entity.TaskLog
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskLogDao {
    @Query("SELECT * FROM task_log ORDER BY createdAt DESC")
    suspend fun getAllTasks(): List<TaskLog>

    @Query("SELECT * FROM task_log WHERE taskType = :taskType ORDER BY createdAt DESC")
    suspend fun getTasksByType(taskType: String): List<TaskLog>

    @Query("SELECT * FROM task_log WHERE id = :id")
    suspend fun getTaskById(id: Long): TaskLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskLog): Long

    @Update
    suspend fun update(task: TaskLog)

    @Delete
    suspend fun delete(task: TaskLog)
}
