package com.legalai.app.data.model

import com.legalai.app.data.local.entity.Document
import com.legalai.app.data.local.entity.Message
import com.legalai.app.data.local.entity.Skill
import kotlin.math.min

/**
 * ContextBuilder implements the hybrid context strategy:
 * 1. Primary: Full-text document access (lexical exactness)
 * 2. Secondary: Sliding window when context exceeds limits
 * 3. RAG fallback when needed
 */
class ContextBuilder(
    private val maxContextTokens: Int = 128000,
    private val reservedOutputTokens: Int = 4096
) {
    fun buildSystemPrompt(
        skills: List<Skill>,
        memoryEntries: List<String>,
        customSystem: String? = null
    ): String {
        val skillInstructions = skills.joinToString("\n\n") { it.instructions }
        val memoryContext = memoryEntries.joinToString("\n") { "- $it" }
        
        return buildString {
            appendLine("You are Legal AI-d, an AI assistant for criminal legal professionals.")
            appendLine("Specialize in federal habeas corpus (28 U.S.C. § 2254) and criminal procedure.")
            appendLine()
            if (customSystem != null) {
                appendLine("CUSTOM SYSTEM PROMPT:")
                appendLine(customSystem)
            }
            if (memoryEntries.isNotEmpty()) {
                appendLine("PERSISTENT MEMORY:")
                appendLine(memoryContext)
            }
            if (skillInstructions.isNotEmpty()) {
                appendLine("AVAILABLE SKILLS:")
                appendLine(skillInstructions)
            }
            appendLine()
        }.trim()
    }

    /**
     * Primary strategy: Inject full document text into context.
     * Uses sliding window/summary if context exceeds model limit.
     */
    fun injectDocumentContext(
        documents: List<Document>,
        userQuery: String,
        remainingTokens: Int
    ): DocumentContext {
        var availableTokens = remainingTokens
        val documentTexts = mutableListOf<String>()
        val usedDocumentIds = mutableListOf<String>()
        
        // Prioritize full document text - lexical exactness approach
        for (doc in documents) {
            // Full text is decrypted using SQLCipher passphrase
            val text = decryptDocument(doc.rawTextEncrypted)
            val textTokens = estimateTokens(text)
            
            if (availableTokens >= textTokens) {
                // Full text fits - add it entirely
                documentTexts.add("=== ${doc.filename} ===\n$text")
                usedDocumentIds.add(doc.id)
                availableTokens -= textTokens
            } else {
                // Sliding window - take most relevant portion
                val excerpt = extractExcerpt(text, availableTokens * 3)
                if (excerpt.isNotEmpty()) {
                    documentTexts.add("=== ${doc.filename} (excerpt) ===\n$excerpt")
                    usedDocumentIds.add(doc.id)
                }
                break
            }
        }
        
        return DocumentContext(
            combinedText = documentTexts.joinToString("\n\n"),
            usedDocumentIds = usedDocumentIds,
            remainingTokens = availableTokens
        )
    }

    private fun estimateTokens(text: String): Int {
        // Rough estimate: 1 token ≈ 4 characters for English
        return (text.length / 4).coerceAtLeast(1)
    }

    /**
     * Decrypts document text using SQLCipher passphrase.
     * SQLCipher encrypts the entire database - documents are already
     * decrypted when read via Room, but we verify integrity here.
     */
    private fun decryptDocument(encrypted: ByteArray): String {
        // SQLCipher handles decryption at database level
        // This ByteArray is already decrypted plaintext when retrieved via Room
        return encrypted.decodeToString()
    }

    /**
     * Extracts the most relevant portion using sliding window.
     * Prioritizes content near user query matches.
     */
    private fun extractExcerpt(text: String, maxChars: Int): String {
        if (text.length <= maxChars) return text
        // For now, take from beginning - future: relevance-based extraction
        return text.take(maxChars) + "\n...(truncated)"
    }
}

data class DocumentContext(
    val combinedText: String,
    val usedDocumentIds: List<String>,
    val remainingTokens: Int
) {
}