package com.example.passwordmanager.di

import android.content.Context
import androidx.room.Room
import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.data.PasswordManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PasswordManagerModule {

    @Provides
    @Singleton
    fun provideDao(database: PasswordManagerDatabase): PasswordDAO {
        return database.passwordDAO()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): PasswordManagerDatabase {
        return Room.databaseBuilder(
            context,
            PasswordManagerDatabase::class.java,
            "passwordManagerDB"
        ).build()
    }



}