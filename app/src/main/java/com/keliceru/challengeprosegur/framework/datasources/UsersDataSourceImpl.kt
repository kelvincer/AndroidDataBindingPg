package com.keliceru.challengeprosegur.framework.datasources

import com.keliceru.challengeprosegur.data.datasources.UsersDataSource
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.framework.database.dao.UserDao
import com.keliceru.challengeprosegur.framework.database.mappers.mapToDBEntity
import com.keliceru.challengeprosegur.framework.database.mappers.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersDataSourceImpl constructor(private val userDao: UserDao) : UsersDataSource {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(userEntity = user.mapToDBEntity())
    }

    override fun getOccupiedSeats(): Flow<Int?> = userDao.getOccupiedSeats()

    override fun getRegisteredUsers(): Flow<List<User>> =
        userDao.getRegisteredUsers().map { it -> it.map { it.mapToDomain() } }

    override suspend fun deleteUser(userId: Int) {
        userDao.deleteUser(userId)
    }
}