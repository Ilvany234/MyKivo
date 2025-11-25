package cl.duoc.kivo.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Delegado top-level para DataStore (nombre del archivo de prefs)
private val Context.dataStore by preferencesDataStore(name = "kivo_prefs")

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val KEY_TOKEN = stringPreferencesKey("key_token")
        private val KEY_LOGGED = booleanPreferencesKey("key_logged")
        private val KEY_EMAIL = stringPreferencesKey("key_email")
    }

    // Flow con el token (nullable)
    val tokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_TOKEN]
    }

    // Flow booleano si está logueado
    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_LOGGED] ?: false
    }

    // Flow con email u otros datos útiles
    val emailFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_EMAIL]
    }

    // Guarda token + marca logged = true
    suspend fun saveSession(token: String, email: String? = null) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
            prefs[KEY_LOGGED] = true
            if (email != null) prefs[KEY_EMAIL] = email
        }
    }

    // Solo marca logged = true/false (útil para pruebas)
    suspend fun setLogged(logged: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LOGGED] = logged
        }
    }

    // Limpia sesión (logout)
    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = ""
            prefs[KEY_LOGGED] = false
            prefs.remove(KEY_EMAIL)
        }
    }
}
