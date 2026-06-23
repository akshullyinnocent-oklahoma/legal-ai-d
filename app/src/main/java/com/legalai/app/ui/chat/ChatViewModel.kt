package com.legalai.app.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legalai.app.data.ChatRepository
import com.legalai.app.data.model.ContextBuilder
import com.legalai.app.data.remote.api.*
import com.legalai.app.data.local.entity.Role
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatViewModel(
    private val repository: ChatRepository,
    private val contextBuilder: ContextBuilder
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun loadProject(projectId: Long) {
        viewModelScope.launch {
            repository.getProjectMessages(projectId)
                .combine(repository.getActiveSkills().asFlow()) { messages, skills ->
                    _uiState.update { it.copy(messages = messages, activeSkills = skills) }
                }.collect()
        }
    }

    fun sendMessage(content: String, projectId: Long, apiClient: BaseApiClient, params: ApiParams) {
        viewModelScope.launch {
            val userMessage = repository.sendMessage(projectId, Role.USER, content)
            
            val memory = repository.getRelevantMemory(projectId)
            val context = contextBuilder.buildSystemPrompt(_uiState.value.activeSkills, memory)
            
            val request = ChatRequest(
                model = params.model,
                messages = buildChatMessages(content, context),
                temperature = params.temperature,
                topP = params.topP,
                topK = params.topK
            )
            
            apiClient.createChatCompletion(request)
        }
    }

    private fun buildChatMessages(userContent: String, systemPrompt: String): List<ChatMessage> {
        return listOf(
            ChatMessage("system", systemPrompt),
            ChatMessage("user", userContent)
        )
    }
}