package com.legalai.app.ui.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TerminalScreen(viewModel: TerminalViewModel) {
    val output by viewModel.output.collectAsState()
    val scrollState = androidx.compose.foundation.lazy.rememberLazyListState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        TerminalOutput(output, scrollState)
        TerminalInput(
            onCommand = { cmd -> viewModel.executeCommand(cmd) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun TerminalOutput(
    output: String,
    scrollState: androidx.compose.foundation.lazy.LazyListState
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier
            .weight(1f)
            .padding(8.dp),
        state = scrollState
    ) {
        items(output.lines().count()) { index ->
            Text(
                text = output.lines().getOrNull(index) ?: "",
                color = Color.Green,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun TerminalInput(
    onCommand: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var command by remember { mutableStateOf("") }
    
    Row(modifier = modifier) {
        Text("\$ ", color = Color.Cyan, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
        BasicTextField(
            value = command,
            onValueChange = { command = it },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                color = Color.Green,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                fontSize = 14.sp
            ),
            onImeActionPerformed = { action ->
                if (action == androidx.compose.ui.text.input.ImeAction.Done) {
                    if (command.isNotBlank()) {
                        onCommand(command)
                        command = ""
                    }
                }
            }
        )
    }
}