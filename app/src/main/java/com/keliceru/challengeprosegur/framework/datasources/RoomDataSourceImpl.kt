package com.keliceru.challengeprosegur.framework.datasources

import com.keliceru.challengeprosegur.data.datasources.RoomDataSource
import com.keliceru.challengeprosegur.framework.database.dao.RoomDao
import com.keliceru.challengeprosegur.framework.database.mappers.mapToDomain
import kotlinx.coroutines.flow.map

class RoomDataSourceImpl constructor(private val roomDao: RoomDao) : RoomDataSource {

    override fun getRooms() = roomDao.getRooms().map { it.map { it.mapToDomain() } }

    override suspend fun updateMondaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateMondayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateTuesdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateTuesdayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateWednesdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateWednesdayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateThursdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateThursdayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateFridaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateFridayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateSaturdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateSaturdayRoom(roomNumber, occupiedSeats)
    }

    override suspend fun updateSundaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDao.updateSundayRoom(roomNumber, occupiedSeats)
    }
}