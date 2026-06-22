package com.legalai.app.data.local

import android.content.Context
import androidx.room.Room
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context, passphrase: CharArray): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            // Initialize SQLCipher
            SQLiteDatabase.loadLibs(context)
            
            val passphraseBytes = passphrase.joinToString("").encodeToByteArray()
            val factory = SupportFactory(passphraseBytes)
            
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "legalai_database.db"
            ).openHelperFactory(factory)
             .fallbackToDestructiveMigration()
             .build()
            
            INSTANCE = instance
            instance
        }
    }
}