package com.example.appcomandav20.features.config.data.resouce

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.appcomandav20.features.config.domain.model.ConfigModel
import com.example.appcomandav20.features.config.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DataStore_NAME = "PHONEBOOK"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_NAME)

class ConfigRepositoryImpl @Inject constructor(
    private val context: Context
) : ConfigRepository {

    companion object{
        val KEYURLBASE = stringPreferencesKey("URLBASE")
        val KEYPORT = stringPreferencesKey("PORT")
    }

    override suspend fun savePhoneBook(configModel: ConfigModel) {
        context.datastore.edit { pref ->
                pref[KEYURLBASE] = configModel.urlBase
                pref[KEYPORT]= configModel.port
        }
    }

    override suspend fun getPhoneBook() =
        context.datastore.data.map { pref ->
            ConfigModel(
                urlBase = pref[KEYURLBASE] ?: "",
                port =  pref[KEYPORT] ?: "",
            )
    }

}