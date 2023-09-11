package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.entitites.Room
import javax.inject.Inject

class GetFilledSeatsUseCase @Inject constructor() {

    fun getFilledSeats(day: Int, room: Int, rooms: List<Room>): Int {
        val occupiedSeats = when (day) {
            0 -> rooms[room].seatsFilledMonday
            1 -> rooms[room].seatsFilledTuesday
            2 -> rooms[room].seatsFilledWednesday
            3 -> rooms[room].seatsFilledThursday
            4 -> rooms[room].seatsFilledFriday
            5 -> rooms[room].seatsFilledSaturday
            6 -> rooms[room].seatsFilledSunday
            else -> 0
        }

        return occupiedSeats
    }
}