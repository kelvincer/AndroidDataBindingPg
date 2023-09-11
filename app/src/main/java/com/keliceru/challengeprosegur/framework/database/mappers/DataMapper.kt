package com.keliceru.challengeprosegur.framework.database.mappers

import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.framework.database.entities.RoomEntity
import com.keliceru.challengeprosegur.framework.database.entities.UserEntity

fun User.mapToDBEntity() = UserEntity(
    roomNumber = roomNumber.toInt(),
    day = day,
    movie = movie,
    price = price.toFloat(),
    quantity = quantity.toInt(),
    dni = dni
)

fun UserEntity.mapToDomain() = User(
    id = id,
    roomNumber = roomNumber.toString(),
    day = day,
    movie = movie,
    price = price.toString(),
    quantity = quantity,
    dni = dni
)

fun Room.mapToDBEntity() = RoomEntity(
    roomNumber = roomNumber.toInt(),
    maxCapacity = maxCapacity,
    seatsFilledMonday = seatsFilledMonday,
    seatsFilledTuesday = seatsFilledTuesday,
    seatsFilledWednesday = seatsFilledWednesday,
    seatsFilledThursday = seatsFilledThursday,
    seatsFilledFriday = seatsFilledFriday,
    seatsFilledSaturday = seatsFilledSaturday,
    seatsFilledSunday = seatsFilledSunday
)

fun RoomEntity.mapToDomain() = Room(
    roomNumber = roomNumber.toString(),
    maxCapacity = maxCapacity,
    seatsFilledMonday = seatsFilledMonday,
    seatsFilledTuesday = seatsFilledTuesday,
    seatsFilledWednesday = seatsFilledWednesday,
    seatsFilledThursday = seatsFilledThursday,
    seatsFilledFriday = seatsFilledFriday,
    seatsFilledSaturday = seatsFilledSaturday,
    seatsFilledSunday = seatsFilledSunday
)
