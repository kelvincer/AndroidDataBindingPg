package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import javax.inject.Inject

class GetOccupiedSeatsUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getOccupiedSeats() = userRepository.getOccupiedSeats()
}