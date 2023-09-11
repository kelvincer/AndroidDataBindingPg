package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.respositories.RoomRepository
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    fun getRooms() = roomRepository.getRooms()
}