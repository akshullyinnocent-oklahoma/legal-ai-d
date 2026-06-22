package com.legalai.app.di

import android.content.Context
import com.legalai.app.data.ChatRepository
import com.legalai.app.data.local.AppDatabase
import com.legalai.app.data.local.DatabaseProvider
import com.legalai.app.data.remote.api.ApiClientFactory
import com.legalai.app.data.SkillParser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { DatabaseProvider.getDatabase(androidContext(), "default_passphrase".toCharArray()) }
    single { get<AppDatabase>().apiConfigDao() }
    single { get<AppDatabase>().projectDao() }
    single { get<AppDatabase>().documentDao() }
    single { get<AppDatabase>().messageDao() }
    single { get<AppDatabase>().skillDao() }
    single { get<AppDatabase>().memoryDao() }
    single { get<AppDatabase>().taskLogDao() }
    single { ChatRepository(get(), get(), get(), get(), get(), get(), get()) }
    single { ApiClientFactory(get()) }
    single { SkillParser() }
}
