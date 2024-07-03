package com.hgh.ttoklip_manger.di

import android.content.Context
import android.content.SharedPreferences
import com.hgh.ttoklip_manger.presentation.utill.SharedPreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStoreModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceUtil(prefs: SharedPreferences): SharedPreferenceUtil {
        return SharedPreferenceUtil(prefs)
    }
}