package com.keliceru.challengeprosegur.data.repositories

import com.keliceru.challengeprosegur.data.datasources.RoomDataSource
import com.keliceru.challengeprosegur.domain.respositories.RoomRepository

class RoomRepositoryImpl constructor(private val roomDataSource: RoomDataSource) : RoomRepository {

    override fun getRooms() = roomDataSource.getRooms()

    override suspend fun updateMondaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateMondaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateTuesdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateTuesdaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateWednesdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateWednesdaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateThursdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateThursdaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateFridaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateFridaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateSaturdaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateSaturdaySeats(roomNumber, occupiedSeats)
    }

    override suspend fun updateSundaySeats(roomNumber: String, occupiedSeats: Int) {
        roomDataSource.updateSundaySeats(roomNumber, occupiedSeats)
    }
}