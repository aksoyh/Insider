package com.aksoyhasan.insider.data.local

import android.content.Context
import com.aksoyhasan.insider.Constants
import com.aksoyhasan.insider.data.Resource
import javax.inject.Inject

class LocalData @Inject constructor(private val context: Context) {

    fun getApplicationName(): Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(Constants.Insider.SHARED_PREFERENCES_FILE_NAME, 0)
        return Resource.Success(sharedPref.getStringSet(Constants.Insider.APPLICATION_NAME, setOf()) ?: setOf())
    }
}