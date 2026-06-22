package com.legalai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.legalai.app.ui.theme.LegalAITheme
import com.legalai.app.ui.MainScreen
import net.sqlcipher.database.SupportFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SQLCipher before Room database
        SupportFactory("")
        enableEdgeToEdge()
        setContent {
            LegalAITheme {
                MainScreen()
            }
        }
    }
}