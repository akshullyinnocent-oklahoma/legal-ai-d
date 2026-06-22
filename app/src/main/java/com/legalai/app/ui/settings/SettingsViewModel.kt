package com.legalai.app.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legalai.app.data.local.entity.ApiProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun updateSelectedProvider(provider: ApiProvider) {
        _uiState.update { it.copy(selectedProvider = provider) }
    }

    fun updateApiKey(key: String) {
        _uiState.update { it.copy(apiKey = key) }
    }

    fun updateBaseUrl(url: String) {
        _uiState.update { it.copy(baseUrl = url) }
    }

    fun saveConfiguration() {
        viewModelScope.launch {
            // Save to encrypted database
        }
    }

    fun testConnection() {
        // Test API connection
    }
}

data class SettingsUiState(
    val selectedProvider: ApiProvider = ApiProvider.OPENAI,
    val apiKey: String = "",
    val baseUrl: String = ""
) {
}