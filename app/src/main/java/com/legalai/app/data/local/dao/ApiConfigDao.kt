package com.legalai.app.data.local.dao

import androidx.room.*
import com.legalai.app.data.local.entity.ApiConfig
import kotlinx.coroutines.flow.Flow

@Dao
interface ApiConfigDao {
    @Query("SELECT * FROM api_configs WHERE is_active = 1")
    suspend fun getActiveConfig(): ApiConfig?

    @Query("SELECT * FROM api_configs")
    fun getAllConfigs(): Flow<List<ApiConfig>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ApiConfig)

    @Update
    suspend fun update(config: ApiConfig)

    @Query("DELETE FROM api_configs WHERE id = :id")
    suspend fun deleteById(id: Long)
}
