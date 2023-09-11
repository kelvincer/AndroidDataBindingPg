package com.keliceru.challengeprosegur.usecases

import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.usecases.GetFilledSeatsUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFilledSeatsUseCaseUnitTest {

    lateinit var getFilledSeatsUseCase: GetFilledSeatsUseCase

    @Before
    fun setUp() {
        getFilledSeatsUseCase = GetFilledSeatsUseCase()
    }

    @Test
    fun `Get occupied seats given some arguments`() {

        val day1 = 0
        val room1 = 0
        val rooms = listOf(
            Room(
                roomNumber = "201",
                maxCapacity = 60,
                seatsFilledMonday = 4,
                seatsFilledTuesday = 10,
                seatsFilledWednesday = 0,
                seatsFilledThursday = 0,
                seatsFilledFriday = 0,
                seatsFilledSaturday = 0,
                seatsFilledSunday = 0
            ),
            Room(
                roomNumber = "202",
                maxCapacity = 60,
                seatsFilledMonday = 4,
                seatsFilledTuesday = 10,
                seatsFilledWednesday = 0,
                seatsFilledThursday = 2,
                seatsFilledFriday = 3,
                seatsFilledSaturday = 0,
                seatsFilledSunday = 0
            ),
        )

        assertEquals(getFilledSeatsUseCase.getFilledSeats(day1, room1, rooms), 4)

        val day2 = 4
        val room2 = 1

        assertEquals(getFilledSeatsUseCase.getFilledSeats(day2, room2, rooms), 3)

    }
}