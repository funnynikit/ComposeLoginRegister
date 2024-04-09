package com.sample.loginregistercompose.util

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConstants {
    val USER_NAME = stringPreferencesKey("userName")
    val USER_MOBILE = stringPreferencesKey("userMobile")
    val USER_EMAIL = stringPreferencesKey("userEmail")
}