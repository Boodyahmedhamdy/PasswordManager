package com.example.passwordmanager.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

// At the top level of your kotlin file:

object DataStoreUtil {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")

    suspend fun isDarkTheme(context: Context): Boolean {
        var value = false
        context.dataStore.data.collect {
            value = it[IS_DARK_THEME] ?: false
        }
        return value
    }

    suspend fun updateIsDarkTheme(context: Context, newIsDarkThemeValue: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_DARK_THEME] = newIsDarkThemeValue
        }
    }
}