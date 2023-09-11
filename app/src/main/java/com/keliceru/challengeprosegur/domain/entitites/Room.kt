package com.keliceru.challengeprosegur.domain.entitites

data class Room(
    val roomNumber: String,
    val maxCapacity: Int,
    val seatsFilledMonday: Int,
    val seatsFilledTuesday: Int,
    val seatsFilledWednesday: Int,
    val seatsFilledThursday: Int,
    val seatsFilledFriday: Int,
    val seatsFilledSaturday: Int,
    val seatsFilledSunday: Int,
)
