package com.keliceru.challengeprosegur.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.keliceru.challengeprosegur.domain.common.cinemaRooms
import com.keliceru.challengeprosegur.framework.database.CinemaDatabase
import com.keliceru.challengeprosegur.framework.database.dao.RoomDao
import com.keliceru.challengeprosegur.framework.database.dao.UserDao
import com.keliceru.challengeprosegur.framework.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class FakeDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CinemaDatabase {
        return Room.inMemoryDatabaseBuilder(context, CinemaDatabase::class.java)
            .allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.beginTransaction()
                    cinemaRooms.forEach {
                        val cv = ContentValues()
                        cv.put("roomNumber", it.roomNumber)
                        cv.put("maxCapacity", 60)
                        cv.put("seatsFilledMonday", 0)
                        cv.put("seatsFilledTuesday", 0)
                        cv.put("seatsFilledWednesday", 0)
                        cv.put("seatsFilledThursday", 0)
                        cv.put("seatsFilledFriday", 0)
                        cv.put("seatsFilledSaturday", 0)
                        cv.put("seatsFilledSunday", 0)
                        db.insert("room", SQLiteDatabase.CONFLICT_IGNORE, cv)
                    }

                    db.setTransactionSuccessful()
                    db.endTransaction()
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: CinemaDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideCinemaRoomDao(database: CinemaDatabase): RoomDao = database.cinemaRoomDao()
}