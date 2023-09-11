package com.keliceru.challengeprosegur.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.keliceru.challengeprosegur.domain.common.cinemaRooms
import com.keliceru.challengeprosegur.framework.database.dao.RoomDao
import com.keliceru.challengeprosegur.framework.database.dao.UserDao
import com.keliceru.challengeprosegur.framework.database.entities.RoomEntity
import com.keliceru.challengeprosegur.framework.database.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(version = 1, entities = arrayOf(UserEntity::class, RoomEntity::class), exportSchema = true)
abstract class CinemaDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun cinemaRoomDao(): RoomDao

    companion object {

        val dbName = "CinemaDB"

        @Volatile
        private var instance: CinemaDatabase? = null

        fun getInstance(context: Context): CinemaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CinemaDatabase {
            return Room.databaseBuilder(context, CinemaDatabase::class.java, dbName)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GlobalScope.launch(Dispatchers.IO) {
                                cinemaRooms.forEach {
                                    instance?.cinemaRoomDao()?.insertRoom(it)
                                }
                            }
                        }
                    }
                ).build()
        }
    }
}