package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.respositories.RoomRepository
import javax.inject.Inject

class UpdateRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend fun updateSeatsOnRoom(room: Room, dayPosition: Int, quantity: Int) {

        when (dayPosition) {
            0 -> roomRepository.updateMondaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledMonday - quantity
            )

            1 -> roomRepository.updateTuesdaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledTuesday - quantity
            )

            2 -> roomRepository.updateWednesdaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledWednesday - quantity
            )

            3 -> roomRepository.updateThursdaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledThursday - quantity
            )

            4 -> roomRepository.updateFridaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledFriday - quantity
            )

            5 -> roomRepository.updateSaturdaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledSaturday - quantity
            )

            6 -> roomRepository.updateSundaySeats(
                room.roomNumber,
                occupiedSeats = room.seatsFilledSunday - quantity
            )
        }
    }
}