package com.keliceru.challengeprosegur.domain.common

import com.keliceru.challengeprosegur.framework.database.entities.RoomEntity

const val errorMessage = "Necesita llenar todos los campos"
const val successMessage = "Registro satisfactorio"
const val totalSeats = 60
val weekDays = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

val cinemaRooms =
    listOf(
        RoomEntity(roomNumber = 201, maxCapacity = totalSeats),
        RoomEntity(roomNumber = 202, maxCapacity = totalSeats),
        RoomEntity(roomNumber = 203, maxCapacity = totalSeats),
        RoomEntity(roomNumber = 204, maxCapacity = totalSeats),
        RoomEntity(roomNumber = 205, maxCapacity = totalSeats)
    )