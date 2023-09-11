package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import javax.inject.Inject

class GetRegisteredUsersUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getRegisteredUsers() = userRepository.getRegisteredUsers()
}