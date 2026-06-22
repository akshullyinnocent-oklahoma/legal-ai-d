package com.legalai.app.ui.terminal

import kotlinx.coroutines.*
import java.io.*

class TerminalSession {
    private var process: Process? = null
    private val outputBuffer = StringBuilder()
    
    fun execute(command: String): String {
        return try {
            process = Runtime.getRuntime().exec(command)
            val reader = BufferedReader(InputStreamReader(process?.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process?.errorStream))
            
            val output = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            while (errorReader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            process?.waitFor()
            output.toString()
        } catch (e: Exception) {
            "Error: ${e.message}"
        } finally {
            process?.destroy()
        }
    }
}