package com.keliceru.challengeprosegur.data.datasources

import com.keliceru.challengeprosegur.domain.entitites.Room
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

    fun getRooms(): Flow<List<Room>>

    suspend fun updateMondaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateTuesdaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateWednesdaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateThursdaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateFridaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateSaturdaySeats(roomNumber: String, occupiedSeats: Int)

    suspend fun updateSundaySeats(roomNumber: String, occupiedSeats: Int)
}