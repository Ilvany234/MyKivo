package cl.duoc.kivo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "kivo_prefs")

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("key_token")
        private val KEY_LOGGED = booleanPreferencesKey("key_logged")
        private val KEY_EMAIL = stringPreferencesKey("key_email")
        // --- ¡SOLUCIÓN! Se añade una clave para guardar el ID del usuario ---
        private val KEY_USER_ID = intPreferencesKey("key_user_id")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { it[KEY_TOKEN] }
    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { it[KEY_LOGGED] ?: false }
    val emailFlow: Flow<String?> = context.dataStore.data.map { it[KEY_EMAIL] }
    // --- ¡SOLUCIÓN! Flow para obtener el ID del usuario ---
    val userIdFlow: Flow<Int?> = context.dataStore.data.map { it[KEY_USER_ID] }

    // --- ¡SOLUCIÓN! Ahora guarda el ID del usuario además del token y el email ---
    suspend fun saveSession(token: String, email: String, userId: Int) {
        context.dataStore.edit {
            it[KEY_TOKEN] = token
            it[KEY_LOGGED] = true
            it[KEY_EMAIL] = email
            it[KEY_USER_ID] = userId
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit {
            it.remove(KEY_TOKEN)
            it.remove(KEY_LOGGED)
            it.remove(KEY_EMAIL)
            it.remove(KEY_USER_ID)
        }
    }
}
