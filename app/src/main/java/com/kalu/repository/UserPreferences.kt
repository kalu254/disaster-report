package com.kalu.repository


import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.kalu.models.LoginResponse
import com.kalu.models.UserProfile
import com.kalu.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "my_data_store"
    )

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun saveUser(
        id: Int,
        username: String,
        email: String
    ){
        dataStore.edit { preferences ->
            preferences[ID] = id
            preferences[USERNAME] = username
            preferences[EMAIL_ADDRESS] = email
        }
    }

    val userProfile: Flow<UserProfile?>
        get() = dataStore.data.map { preferences ->
            UserProfile(preferences[ID],preferences[USERNAME],preferences[EMAIL_ADDRESS])
        }


    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val ID = preferencesKey<Int>("id")
        private val USERNAME = preferencesKey<String>("username")
        private val EMAIL_ADDRESS = preferencesKey<String>("email_address")

    }

}
