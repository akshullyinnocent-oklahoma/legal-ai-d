package com.legalai.app

import android.app.Application
import com.legalai.app.di.AppModule
import net.sqlcipher.database.SQLiteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LegalAIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize SQLCipher
        SQLiteDatabase.loadLibs(this)
        
        // Initialize Koin
        startKoin {
            androidContext(this@LegalAIApplication)
            modules(AppModule)
        }
    }
}