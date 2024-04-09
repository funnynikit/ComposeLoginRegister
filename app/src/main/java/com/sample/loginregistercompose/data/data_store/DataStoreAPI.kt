package com.sample.loginregistercompose.data.data_store

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreAPI {
    suspend fun <T> getPreferenceValue(key: Preferences.Key<T>, defaultValue: T):T
    suspend fun <T> putPreferenceValue(key: Preferences.Key<T>,value:T)
    suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearPreference()
}