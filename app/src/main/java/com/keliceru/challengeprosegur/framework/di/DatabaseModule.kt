package com.keliceru.challengeprosegur.framework.di

import android.content.Context
import com.keliceru.challengeprosegur.framework.database.CinemaDatabase
import com.keliceru.challengeprosegur.framework.database.dao.RoomDao
import com.keliceru.challengeprosegur.framework.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CinemaDatabase {
        return CinemaDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: CinemaDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideCinemaRoomDao(database: CinemaDatabase): RoomDao = database.cinemaRoomDao()

}