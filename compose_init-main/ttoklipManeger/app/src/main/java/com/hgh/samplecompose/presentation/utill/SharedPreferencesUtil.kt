package com.hgh.samplecompose.presentation.utill

import android.content.SharedPreferences

class SharedPreferenceUtil(private val prefs: SharedPreferences) {

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, boolean: Boolean) {
        prefs.edit().putBoolean(key, boolean).apply()
    }
}