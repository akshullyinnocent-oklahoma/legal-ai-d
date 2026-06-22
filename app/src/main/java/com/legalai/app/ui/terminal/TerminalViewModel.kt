package com.legalai.app.ui.terminal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TerminalViewModel : ViewModel() {
    private val _output = MutableStateFlow("")
    val output: StateFlow<String> = _output

    private val session = TerminalSession()

    fun executeCommand(command: String) {
        viewModelScope.launch {
            val result = session.execute(command)
            _output.value += "$ $command\n$result\n"
        }
    }
}