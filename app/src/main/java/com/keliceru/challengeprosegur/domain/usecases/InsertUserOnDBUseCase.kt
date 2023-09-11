package com.keliceru.challengeprosegur.domain.usecases

import com.keliceru.challengeprosegur.domain.common.BaseResult
import com.keliceru.challengeprosegur.domain.common.errorMessage
import com.keliceru.challengeprosegur.domain.common.successMessage
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.respositories.RoomRepository
import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import java.text.DecimalFormat
import javax.inject.Inject

class InsertUserOnDBUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository,
) {

    suspend fun insertNewRecord(user: User, filledSeats: Int): BaseResult<String, String> {

        if (user.onError) {
            return BaseResult.Error(errorMessage)
        }

        val totalAmount = user.price.toFloat() * user.quantity

        val totalWithDiscount: Float = when (user.dayPosition) {
            0, 2, 3 -> totalAmount * 0.7f
            1 -> totalAmount * 0.5f
            4, 5, 6 -> totalAmount * 1.4f
            else -> 0f
        }

        val df = DecimalFormat("#.##")

        userRepository.insertUser(user = user.copy(price = df.format(totalWithDiscount)))

        when (user.dayPosition) {
            0 -> roomRepository.updateMondaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            1 -> roomRepository.updateTuesdaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            2 -> roomRepository.updateWednesdaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            3 -> roomRepository.updateThursdaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            4 -> roomRepository.updateFridaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            5 -> roomRepository.updateSaturdaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )

            6 -> roomRepository.updateSundaySeats(
                user.roomNumber,
                occupiedSeats = user.quantity + filledSeats
            )
        }

        return BaseResult.Success(successMessage)
    }
}