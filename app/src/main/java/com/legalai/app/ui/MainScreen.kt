package com.legalai.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.legalai.app.ui.chat.ChatScreen
import com.legalai.app.ui.settings.SettingsScreen
import com.legalai.app.ui.projects.ProjectListScreen
import com.legalai.app.ui.terminal.TerminalScreen

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(MainTab.CHAT) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                MainTab.CHAT -> ChatScreen(viewModel = androidx.lifecycle.viewmodel.compose.viewModel())
                MainTab.PROJECTS -> ProjectListScreen()
                MainTab.TERMINAL -> TerminalScreen()
                MainTab.SETTINGS -> SettingsScreen(viewModel = androidx.lifecycle.viewmodel.compose.viewModel())
            }
        }
    }
}

enum class MainTab(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    CHAT("Chat", Icons.Filled.Chat),
    PROJECTS("Projects", Icons.Filled.Folder),
    TERMINAL("Terminal", Icons.Filled.Terminal),
    SETTINGS("Settings", Icons.Filled.Settings)
}
