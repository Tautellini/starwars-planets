package com.example.starwarsplanets.shared.services

import android.content.Context
import android.content.SharedPreferences
import com.github.florent37.application.provider.ApplicationProvider.application

object PreferencesService {

    fun getSharedPreferences(name: String, context: Context? = application): SharedPreferences? {
        return context?.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}