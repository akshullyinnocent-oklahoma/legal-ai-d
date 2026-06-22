package com.legalai.app.di

import android.content.Context
import com.legalai.app.data.local.AppDatabase
import com.legalai.app.data.local.DatabaseProvider
import com.legalai.app.data.remote.api.ApiClientFactory
import com.legalai.app.data.ChatRepository
import com.legalai.app.data.SkillParser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseProvider.getDatabase(androidContext(), get()) }
    single { get<AppDatabase>().apiConfigDao() }
    single { get<AppDatabase>().projectDao() }
    single { get<AppDatabase>().documentDao() }
    single { get<AppDatabase>().messageDao() }
    single { get<AppDatabase>().taskLogDao() }
    single { get<AppDatabase>().memoryDao() }
    single { ApiClientFactory(get()) }
    single { ChatRepository(get(), get()) }
    single { SkillParser() }
}
