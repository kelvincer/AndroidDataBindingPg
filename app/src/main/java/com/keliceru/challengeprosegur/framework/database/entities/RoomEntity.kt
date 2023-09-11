package com.keliceru.challengeprosegur.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "roomId")
    val id: Long = 0,
    val roomNumber: Int,
    val maxCapacity: Int,
    val seatsFilledMonday: Int = 0,
    val seatsFilledTuesday: Int = 0,
    val seatsFilledWednesday: Int = 0,
    val seatsFilledThursday: Int = 0,
    val seatsFilledFriday: Int = 0,
    val seatsFilledSaturday: Int = 0,
    val seatsFilledSunday: Int = 0,
)
