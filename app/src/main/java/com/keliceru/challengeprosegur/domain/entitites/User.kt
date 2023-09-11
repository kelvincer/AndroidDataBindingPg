package com.keliceru.challengeprosegur.domain.entitites

data class User(
    val id: Long = 0,
    val roomNumber: String,
    val day: String,
    val dayPosition: Int = 0,
    val movie: String,
    val price: String,
    val quantity: Int,
    val dni: String,
    val onError: Boolean = false,
)

