package com.keliceru.challengeprosegur.domain.respositories

import com.keliceru.challengeprosegur.domain.entitites.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: User)

    fun getOccupiedSeats(): Flow<Int?>

    fun getRegisteredUsers(): Flow<List<User>>

    suspend fun deleteUser(userId: Int)
}