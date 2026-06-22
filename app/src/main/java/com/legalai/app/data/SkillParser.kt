package com.legalai.app.data

import com.legalai.app.data.local.entity.Skill
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.yaml.snakeyaml.Yaml
import java.io.File

class SkillParser {
    private val yaml = Yaml()

    @Serializable
    data class SkillFrontmatter(
        val name: String,
        val description: String,
        val compatibility: String? = null,
        val metadata: Map<String, String>? = null
    )

    fun parseSkillFile(file: File): Skill {
        val content = file.readText()
        val (frontmatter, body) = parseFrontmatter(content)
        
        val fm = yaml.loadAs(frontmatter, SkillFrontmatter::class.java)
        
        return Skill(
            name = fm.name,
            description = fm.description,
            instructions = body,
            tags = fm.metadata?.get("tags") ?: "[]",
            isBuiltIn = false
        )
    }

    private fun parseFrontmatter(content: String): Pair<String, String> {
        val lines = content.lines()
        if (lines.firstOrNull()?.trim() != "---") return "" to content
        
        val endFence = lines.indexOfFirst { it.trim() == "---" && lines.indexOf(it) > 0 }
        if (endFence == -1) return "" to content
        
        val frontmatter = lines.subList(1, endFence).joinToString("\n")
        val body = lines.subList(endFence + 1, lines.size).joinToString("\n")
        
        return frontmatter to body
    }
}