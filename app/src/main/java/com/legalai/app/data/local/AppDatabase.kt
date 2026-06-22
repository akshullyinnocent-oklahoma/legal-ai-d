package com.legalai.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.legalai.app.data.local.dao.*
import com.legalai.app.data.local.entity.*

@Database(
    entities = [
        ApiConfig::class,
        Project::class,
        Document::class,
        Message::class,
        Skill::class,
        MemoryEntry::class,
        TaskLog::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apiConfigDao(): ApiConfigDao
    abstract fun projectDao(): ProjectDao
    abstract fun documentDao(): DocumentDao
    abstract fun messageDao(): MessageDao
    abstract fun skillDao(): SkillDao
    abstract fun memoryDao(): MemoryDao
    abstract fun taskLogDao(): TaskLogDao
}