package com.hgh.samplecompose.di

import android.content.Context
import android.content.SharedPreferences
import com.hgh.samplecompose.presentation.utill.SharedPreferenceUtil
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object LocalStoreModule {

//    @Provides
//    @Singleton
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
//        return context.getSharedPreferences("user", Context.MODE_PRIVATE)
//    }
//
//    @Provides
//    @Singleton
//    fun providePreferenceUtil(prefs: SharedPreferences): SharedPreferenceUtil {
//        return SharedPreferenceUtil(prefs)
//    }
}