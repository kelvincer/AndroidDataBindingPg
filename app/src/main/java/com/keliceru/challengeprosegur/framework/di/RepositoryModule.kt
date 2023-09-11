package com.keliceru.challengeprosegur.framework.di

import com.keliceru.challengeprosegur.data.datasources.RoomDataSource
import com.keliceru.challengeprosegur.data.datasources.UsersDataSource
import com.keliceru.challengeprosegur.data.repositories.RoomRepositoryImpl
import com.keliceru.challengeprosegur.data.repositories.UserRepositoryImpl
import com.keliceru.challengeprosegur.domain.respositories.RoomRepository
import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UsersDataSource): UserRepository =
        UserRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideRoomRepository(roomDataSource: RoomDataSource): RoomRepository =
        RoomRepositoryImpl(roomDataSource)
}
