package com.legalai.app.data.local.dao

import androidx.room.*
import com.legalai.app.data.local.entity.Skill
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {
    @Query("SELECT * FROM skills ORDER BY name ASC")
    fun getAllSkills(): Flow<List<Skill>>

    @Query("SELECT * FROM skills WHERE isActive = 1")
    suspend fun getActiveSkills(): List<Skill>

    @Query("SELECT * FROM skills WHERE id = :id")
    suspend fun getSkillById(id: String): Skill?

    @Query("SELECT * FROM skills WHERE name LIKE :query OR tags LIKE :query")
    suspend fun searchSkills(query: String): List<Skill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skill: Skill)

    @Update
    suspend fun update(skill: Skill)

    @Delete
    suspend fun delete(skill: Skill)
}