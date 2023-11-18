package com.iiamir.cryptofeargreed.data.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.iiamir.cryptofeargreed.utils.Constants

object PreferencesKey {
    val isInDarkModeKey =
        booleanPreferencesKey(name = Constants.IS_IN_DARK_MODE_PREF_KEY)

    val lastYearIndexIsGone =
        booleanPreferencesKey(name = Constants.LAST_YEAR_INDEX_IS_GONE_PREF_KEY)
}