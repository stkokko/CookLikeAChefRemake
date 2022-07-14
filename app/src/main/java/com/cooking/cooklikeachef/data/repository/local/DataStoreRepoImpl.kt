package com.cooking.cooklikeachef.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import kotlinx.coroutines.flow.first

class DataStoreRepoImpl(private val dataStore: DataStore<Preferences>) : DataStoreRepo {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
    }


    override suspend fun setUserLoginState(isLoggedIn: Boolean) {
        dataStore.edit { user ->
            user[IS_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun getUserLoginState(): Boolean {
        val initialPrefs = dataStore.data.first().toPreferences()
        return initialPrefs[IS_LOGGED_IN] ?: false
    }
}