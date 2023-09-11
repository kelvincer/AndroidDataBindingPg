package com.keliceru.challengeprosegur.usecases

import com.keliceru.challengeprosegur.domain.common.BaseResult
import com.keliceru.challengeprosegur.domain.common.errorMessage
import com.keliceru.challengeprosegur.domain.common.successMessage
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.respositories.RoomRepository
import com.keliceru.challengeprosegur.domain.respositories.UserRepository
import com.keliceru.challengeprosegur.domain.usecases.InsertUserOnDBUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class InsertUserOnDBUseCaseUnitTest {

    private lateinit var insertUserOnDBUseCase: InsertUserOnDBUseCase
    private lateinit var userRepository: UserRepository
    private lateinit var roomRepository: RoomRepository

    @Before
    fun setUp() {
        userRepository = mockk()
        roomRepository = mockk()
        insertUserOnDBUseCase = InsertUserOnDBUseCase(userRepository, roomRepository)
    }

    @Test
    fun `When user has onError on true, we'll get error response`() = runTest {

        // Given
        val user: User =
            User(
                roomNumber = "",
                day = "",
                dayPosition = 0,
                movie = "",
                price = "",
                quantity = 2,
                dni = "",
                onError = true
            )

        // Then
        assertEquals(insertUserOnDBUseCase.insertNewRecord(user, 3), BaseResult.Error(errorMessage))
    }

    @Test
    fun `When User doesn't have error we'll get a success response`() = runTest {

        // Given
        val user =
            User(
                roomNumber = "205",
                day = "Miercoles",
                dayPosition = 2,
                movie = "Movie",
                price = "10",
                quantity = 3,
                dni = "43434343",
                onError = false
            )

        coEvery { userRepository.insertUser(any()) } returns Unit
        coEvery { roomRepository.updateWednesdaySeats(any(), any()) } returns Unit

        // Then
        assertEquals(
            insertUserOnDBUseCase.insertNewRecord(user, 4),
            BaseResult.Success(successMessage)
        )
    }
}