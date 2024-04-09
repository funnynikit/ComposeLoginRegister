import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.sample.loginregistercompose.data.data_store.DataStoreAPI
import com.sample.loginregistercompose.util.DataStoreConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "MyDataStore",
    produceMigrations = ::sharedPreferencesMigration
)

private fun sharedPreferencesMigration(context: Context) =
    listOf(SharedPreferencesMigration(context, "MyPreferences"))

class DataStoreManager(context: Context) : DataStoreAPI {

    private val dataSource = context.dataStore

    override suspend fun <T> getPreferenceValue(key: Preferences.Key<T>, defaultValue: T): T =
        dataSource.data.first()[key] ?: defaultValue

    override suspend fun <T> putPreferenceValue(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun <T> clearPreference() {
    }

    suspend fun <T> clearAllPreference() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun saveUserName(value: String) {
        putPreferenceValue(DataStoreConstants.USER_NAME, value)
    }

    suspend fun getUserName(): String {
        return getPreferenceValue(DataStoreConstants.USER_NAME, "")
    }

    suspend fun saveUserMobile(value: String) {
        putPreferenceValue(DataStoreConstants.USER_MOBILE, value)
    }

    suspend fun getUserMobile(): String {
        return getPreferenceValue(DataStoreConstants.USER_MOBILE, "")
    }

    suspend fun saveUserEmail(value: String) {
        putPreferenceValue(DataStoreConstants.USER_EMAIL, value)
    }

    suspend fun getUserEmail(): String {
        return getPreferenceValue(DataStoreConstants.USER_EMAIL, "")
    }
}