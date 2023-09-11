package com.keliceru.challengeprosegur.data.repositories

import com.keliceru.challengeprosegur.data.datasources.UsersDataSource
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl constructor(private val usersDataSource: UsersDataSource) :
    UserRepository {

    override suspend fun insertUser(user: User) {
        usersDataSource.insertUser(user = user)
    }

    override fun getOccupiedSeats(): Flow<Int?> = usersDataSource.getOccupiedSeats()

    override fun getRegisteredUsers(): Flow<List<User>> = usersDataSource.getRegisteredUsers()

    override suspend fun deleteUser(userId: Int) {
        usersDataSource.deleteUser(userId)
    }
}