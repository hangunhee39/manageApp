package com.hgh.ttoklip_manger.di

import com.hgh.ttoklip_manger.data.repository.NewsRepositoryImpl
import com.hgh.ttoklip_manger.data.repository.NoticeRepositoryImpl
import com.hgh.ttoklip_manger.data.source.remote.api.NewsService
import com.hgh.ttoklip_manger.data.source.remote.api.NoticeService
import com.hgh.ttoklip_manger.domain.repository.NewsRepository
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(api: NewsService): NewsRepository =
        NewsRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesNoticeRepository(api: NoticeService): NoticeRepository =
        NoticeRepositoryImpl(api)

}