package com.keliceru.challengeprosegur.data.datasources

import com.keliceru.challengeprosegur.domain.entitites.User
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {

    suspend fun insertUser(user: User)

    fun getOccupiedSeats(): Flow<Int?>

    fun getRegisteredUsers(): Flow<List<User>>

    suspend fun deleteUser(userId: Int)
}