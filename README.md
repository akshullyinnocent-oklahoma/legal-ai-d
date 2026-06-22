# Legal AI-d Android Project

## Overview
Native Android BYOK Legal AI Assistant for criminal legal professionals. AOSP-compatible with no Google Play Services dependencies.

## Architecture

### Tech Stack
- **UI**: Jetpack Compose (Material 3) - AMOLED Dark Theme
- **Database**: Room + SQLCipher (encrypted) with FTS5
- **Networking**: Ktor Client (OkHttp engine)
- **DI**: Koin (AOSP-compatible, no Hilt/Google)
- **PDF**: PDFBox Android
- **Markdown**: compose-markdown

## Project Structure Complete

```
LegalAI-d/
├── build.gradle.kts           # Root Gradle config
├── settings.gradle.kts        # Project settings
├── gradle.properties          # Gradle properties
├── database_schema.sql        # Complete SQL schema reference
├── README.md                  # This file
├── PROJECT_STRUCTURE.md       # Detailed index
├── app/
│   ├── build.gradle.kts       # App dependencies
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/legalai/app/
│   │   │   ├── MainActivity.kt           # Compose entry point
│   │   │   ├── LegalAIApplication.kt     # Koin initialization
│   │   │   ├── di/
│   │   │   │   ├── AppModule.kt          # Koin DI module
│   │   │   │   └── DatabaseModule.kt     # Database provider
│   │   │   ├── data/
│   │   │   │   ├── ChatRepository.kt     # Main repository
│   │   │   │   ├── SkillParser.kt        # YAML frontmatter parser
│   │   │   │   ├── local/
│   │   │   │   │   ├── AppDatabase.kt      # Room database
│   │   │   │   │   ├── Converters.kt       # Type converters
│   │   │   │   │   ├── DatabaseProvider.kt   # SQLCipher init
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── ApiConfig.kt      # API configuration entity
│   │   │   │   │   │   ├── ApiProvider.kt    # Provider enum
│   │   │   │   │   │   ├── Project.kt        # Case entity
│   │   │   │   │   │   ├── Document.kt       # Full-text document storage
│   │   │   │   │   │   ├── Message.kt        # Chat messages
│   │   │   │   │   │   ├── Role.kt           # Message roles enum
│   │   │   │   │   │   ├── Skill.kt          # Skill metadata
│   │   │   │   │   │   ├── MemoryEntry.kt      # Persistent memory
│   │   │   │   │   │   └── TaskLog.kt         # Task logging
│   │   │   │   │   └── dao/
│   │   │   │   │       ├── ApiConfigDao.kt   # API config queries
│   │   │   │   │       ├── DocumentDao.kt    # Document queries
│   │   │   │   │       ├── MessageDao.kt     # Message queries
│   │   │   │   │   │   ├── ProjectDao.kt     # Project queries
│   │   │   │   │   │   ├── SkillDao.kt       # Skill queries
│   │   │   │   │   │   ├── MemoryDao.kt      # Memory queries
│   │   │   │   │   │   └── TaskLogDao.kt     # Task log queries
│   │   │   │   ├── remote/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   ├── ApiClientFactory.kt    # Ktor factory
│   │   │   │   │   │   ├── BaseApiClient.kt       # Abstract client interface
│   │   │   │   │   │   ├── ChatModels.kt          # Request/Response models
│   │   │   │   │   │   ├── OpenAiCompatibleClient.kt
│   │   │   │   │   │   └── AnthropicClient.kt
│   │   │   │   └── model/
│   │   │   │   │   ├── ContextBuilder.kt    # Hybrid context strategy
│   │   │   │   │   └── ApiParams.kt         # Chat parameters
│   │   │   ├── ui/
│   │   │   │   ├── MainScreen.kt          # Main navigation
│   │   │   │   ├── theme/
│   │   │   │   │   ├── LegalAIColors.kt     # AMOLED black/Cyan colors
│   │   │   │   │   └── Theme.kt           # Material3 theme
│   │   │   │   ├── chat/
│   │   │   │   │   ├── ChatScreen.kt        # Main chat UI
│   │   │   │   │   └── ChatViewModel.kt     # Chat state management
│   │   │   │   ├── settings/
│   │   │   │   │   ├── SettingsScreen.kt    # BYOK API config
│   │   │   │   │   └── SettingsViewModel.kt
│   │   │   │   ├── terminal/
│   │   │   │   │   ├── TerminalScreen.kt
│   │   │   │   │   └── TerminalViewModel.kt
│   │   │   └── service/
│   │   │   │   └── AgentService.kt        # Foreground service
│   └── assets/python/
│   └── proguard-rules.pro
```

## Key Implementation Details

### 1. Hybrid Context Strategy (ContextBuilder.kt)
- **Primary**: Full-text document storage in encrypted BLOBs
- **Secondary**: Sliding window when context exceeds model limit
- **Fallback**: FTS5 search + RAG vector databases

### 2. Multi-Provider API Engine
Supports OpenAI, Anthropic, OpenRouter, Groq, Cerebras, NVIDIA, DeepSeek
via OpenAI-compatible and Anthropic-specific endpoints.

### 3. Encrypted Storage
All sensitive data encrypted via SQLCipher, including:
- API keys (api_key_encrypted BLOB)
- Document full-text (raw_text_encrypted BLOB)
- Memory entries

### 4. Agentic Memory System
Persistent memory with TaskLog for tracking executed operations
across sessions.

### 5. AOSP Compatibility
- Uses Koin instead of Hilt (no Google dependencies)
- SQLCipher for local encryption
- OkHttp for networking (no Play Services)

## Setup

### Android NDK
The project uses native code. Download the NDK:
- URL: https://dl.google.com/android/repository/android-ndk-r27d-linux.zip

### Build
```bash
./gradlew assembleDebug
```

### AOSP Integration
```bash
# Clone AOSP source
repo init -u https://android.googlesource.com/platform/manifest
# Copy LegalAI-d module to packages/apps/
```