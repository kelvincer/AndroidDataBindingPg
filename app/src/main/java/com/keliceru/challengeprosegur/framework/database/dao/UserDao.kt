package com.keliceru.challengeprosegur.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keliceru.challengeprosegur.framework.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT SUM(quantity) FROM User")
    fun getOccupiedSeats(): Flow<Int?>

    @Query("SELECT * FROM User")
    fun getRegisteredUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM User WHERE userId = :userId")
    suspend fun deleteUser(userId: Int)
}