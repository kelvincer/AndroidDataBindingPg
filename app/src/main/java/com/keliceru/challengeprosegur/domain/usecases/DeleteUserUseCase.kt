package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun deleteUser(id: Int) {
        userRepository.deleteUser(id)
    }

}