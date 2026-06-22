package com.legalai.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LegalAIColors.CYAN_ACCENT,
    onPrimary = Color.Black,
    primaryContainer = LegalAIColors.CYAN_DARK,
    onPrimaryContainer = Color.White,
    
    secondary = LegalAIColors.CYAN_LIGHT,
    onSecondary = Color.Black,
    
    background = LegalAIColors.AMOLED_BLACK,
    onBackground = LegalAIColors.ON_BACKGROUND,
    
    surface = LegalAIColors.SURFACE_DARK,
    onSurface = LegalAIColors.ON_SURFACE,
    
    surfaceVariant = LegalAIColors.SURFACE_VARIANT,
    onSurfaceVariant = LegalAIColors.ON_SURFACE_VARIANT,
    
    outline = LegalAIColors.CYAN_ACCENT.copy(alpha = 0.5f)
)

@Composable
fun LegalAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography(),
        content = content
    )
}