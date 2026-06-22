package com.legalai.app.data

import com.legalai.app.data.local.dao.*
import com.legalai.app.data.local.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val documentDao: DocumentDao,
    private val skillDao: SkillDao,
    private val memoryDao: MemoryDao
) {
    suspend fun getProjectMessages(projectId: Long): Flow<List<Message>> {
        return messageDao.getMessagesByProject(projectId)
    }

    suspend fun sendMessage(
        projectId: Long,
        role: Role,
        content: String
    ): Message {
        val message = Message(
            projectId = projectId,
            role = role,
            content = content,
            tokenCount = content.length / 4
        )
        messageDao.insert(message)
        return message
    }

    suspend fun getActiveSkills(): List<Skill> {
        return skillDao.getActiveSkills()
    }

    suspend fun getRelevantMemory(projectId: Long, limit: Int = 50): List<String> {
        return memoryDao.getRecentMemory(projectId, limit)
            .map { it.content }
    }

    suspend fun createMemory(
        projectId: Long?,
        type: MemoryType,
        content: String,
        context: String? = null
    ) {
        val entry = MemoryEntry(
            projectId = projectId,
            type = type,
            content = content,
            context = context
        )
        memoryDao.insert(entry)
    }
}