package com.keliceru.challengeprosegur.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val id: Long = 0,
    val roomNumber: Int,
    val day: String,
    val movie: String,
    val price: Float,
    val quantity: Int,
    val dni: String
)
