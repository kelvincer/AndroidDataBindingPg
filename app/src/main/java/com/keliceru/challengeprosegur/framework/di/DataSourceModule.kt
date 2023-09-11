package com.keliceru.challengeprosegur.framework.di

import com.keliceru.challengeprosegur.data.datasources.RoomDataSource
import com.keliceru.challengeprosegur.data.datasources.UsersDataSource
import com.keliceru.challengeprosegur.framework.database.dao.RoomDao
import com.keliceru.challengeprosegur.framework.database.dao.UserDao
import com.keliceru.challengeprosegur.framework.datasources.RoomDataSourceImpl
import com.keliceru.challengeprosegur.framework.datasources.UsersDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideUserDataSource(userDao: UserDao): UsersDataSource = UsersDataSourceImpl(userDao)

    @Provides
    @Singleton
    fun provideRoomDataSource(roomDao: RoomDao): RoomDataSource = RoomDataSourceImpl(roomDao)
}