package com.keliceru.challengeprosegur.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keliceru.challengeprosegur.framework.database.entities.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert
    suspend fun insertRoom(roomEntity: RoomEntity)

    @Query("SELECT * FROM room")
    fun getRooms(): Flow<List<RoomEntity>>

    @Query(
        """
        UPDATE room
        SET seatsFilledMonday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateMondayRoom(roomNumber: String, occupiedSeats: Int)

    @Query(
        """
        UPDATE room
        SET seatsFilledTuesday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateTuesdayRoom(roomNumber: String, occupiedSeats: Int)

    @Query(
        """
        UPDATE room
        SET seatsFilledWednesday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateWednesdayRoom(roomNumber: String, occupiedSeats: Int)


    @Query(
        """
        UPDATE room
        SET seatsFilledThursday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateThursdayRoom(roomNumber: String, occupiedSeats: Int)

    @Query(
        """
        UPDATE room
        SET seatsFilledFriday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateFridayRoom(roomNumber: String, occupiedSeats: Int)

    @Query(
        """
        UPDATE room
        SET seatsFilledSaturday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateSaturdayRoom(roomNumber: String, occupiedSeats: Int)


    @Query(
        """
        UPDATE room
        SET seatsFilledSunday = :occupiedSeats
        WHERE roomNumber = :roomNumber
       """
    )
    suspend fun updateSundayRoom(roomNumber: String, occupiedSeats: Int)

}