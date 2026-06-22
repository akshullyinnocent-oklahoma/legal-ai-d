# Legal AI-d - Complete Project Index

Native Android BYOK Legal AI Assistant. AOSP-compatible.

## Directory Structure

```
LegalAI-d/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── database_schema.sql
├── README.md
├── PROJECT_STRUCTURE.md
├── app/
│   ├── build.gradle.kts
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/legalai/app/
│   │   │   ├── MainActivity.kt
│   │   │   ├── LegalAIApplication.kt
│   │   │   ├── di/
│   │   │   │   ├── AppModule.kt
│   │   │   │   └── DatabaseModule.kt
│   │   │   ├── data/
│   │   │   │   ├── ChatRepository.kt
│   │   │   │   ├── SkillParser.kt
│   │   │   │   ├── local/
│   │   │   │   │   ├── AppDatabase.kt
│   │   │   │   │   ├── Converters.kt
│   │   │   │   │   ├── DatabaseProvider.kt
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── ApiConfig.kt
│   │   │   │   │   │   ├── ApiProvider.kt
│   │   │   │   │   │   ├── Project.kt
│   │   │   │   │   │   ├── Document.kt
│   │   │   │   │   │   ├── Message.kt
│   │   │   │   │   │   ├── Role.kt
│   │   │   │   │   │   ├── Skill.kt
│   │   │   │   │   │   ├── MemoryEntry.kt
│   │   │   │   │   │   └── TaskLog.kt
│   │   │   │   │   └── dao/
│   │   │   │   │       ├── ApiConfigDao.kt
│   │   │   │   │       ├── DocumentDao.kt
│   │   │   │   │       ├── MessageDao.kt
│   │   │   │   │       ├── ProjectDao.kt
│   │   │   │   │       ├── SkillDao.kt
│   │   │   │   │       ├── MemoryDao.kt
│   │   │   │   │       └── TaskLogDao.kt
│   │   │   │   ├── remote/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   ├── ApiClientFactory.kt
│   │   │   │   │   │   ├── BaseApiClient.kt
│   │   │   │   │   │   ├── ChatModels.kt
│   │   │   │   │   │   ├── OpenAiCompatibleClient.kt
│   │   │   │   │   │   └── AnthropicClient.kt
│   │   │   │   │   └── model/
│   │   │   │   │       ├── ContextBuilder.kt
│   │   │   │   │       └── ApiParams.kt
│   │   │   ├── ui/
│   │   │   │   ├── MainScreen.kt
│   │   │   │   ├── theme/
│   │   │   │   │   ├── LegalAIColors.kt
│   │   │   │   │   └── Theme.kt
│   │   │   │   ├── chat/
│   │   │   │   │   ├── ChatScreen.kt
│   │   │   │   │   └── ChatViewModel.kt
│   │   │   │   ├── settings/
│   │   │   │   │   ├── SettingsScreen.kt
│   │   │   │   │   └── SettingsViewModel.kt
│   │   │   │   ├── terminal/
│   │   │   │   │   ├── TerminalScreen.kt
│   │   │   │   │   └── TerminalViewModel.kt
│   │   │   │   └── projects/
│   │   │   │       └── ProjectListScreen.kt
│   │   │   └── service/
│   │   │       └── AgentService.kt
│   │   └── res/
│   │       └── values/
│   │           ├── colors.xml
│   │           └── themes.xml
```

## NDK Reference
Download NDK r27d: https://dl.google.com/android/repository/android-ndk-r27d-linux.zip
