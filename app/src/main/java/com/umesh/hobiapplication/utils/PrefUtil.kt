package com.umesh.hobiapplication.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "app_pref"
)

class PrefUtil (private val context: Context) {



    companion object{
        val LAST_LOGINID = longPreferencesKey(name = "id")
        val USR_LOGIN = stringPreferencesKey(name = "AllReadyLogin")
        val SIGNUP_SUCCESS = stringPreferencesKey(name = "SignupSuccess")
    }

    suspend fun saveLastLoginID(id: Long){
        context.dataStore.edit { preferences ->
            preferences[LAST_LOGINID] = id
        }
    }

    val getLastLoginId: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_LOGINID] ?: -1L
        }
    suspend fun saveLastLogin(s: String){
        context.dataStore.edit { preferences ->
            preferences[USR_LOGIN] = s
        }
    }

    val getLastLogin: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USR_LOGIN] ?: "No"
        }

    suspend fun saveSigupSuccess(string: String){
        context.dataStore.edit { preferences ->
            preferences[SIGNUP_SUCCESS] = string
        }
    }

    val getSignupSuccess: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[SIGNUP_SUCCESS] ?: "No"
        }
}