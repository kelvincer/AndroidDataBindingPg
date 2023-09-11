package com.keliceru.challengeprosegur.viewmodels

import app.cash.turbine.test
import com.keliceru.challengeprosegur.domain.common.BaseResult
import com.keliceru.challengeprosegur.domain.common.successMessage
import com.keliceru.challengeprosegur.domain.usecases.GetFilledSeatsUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRoomsUseCase
import com.keliceru.challengeprosegur.domain.usecases.InsertUserOnDBUseCase
import com.keliceru.challengeprosegur.presentation.viewmodels.RegisterViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RegisterViewModelUnitTest {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var insertUserOnDBUseCase: InsertUserOnDBUseCase
    private lateinit var getRoomsUseCase: GetRoomsUseCase
    private lateinit var getFilledSeatsUseCase: GetFilledSeatsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        insertUserOnDBUseCase = mockk()
        getRoomsUseCase = mockk()
        getFilledSeatsUseCase = mockk()
        viewModel = RegisterViewModel(insertUserOnDBUseCase, getRoomsUseCase, getFilledSeatsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Success new user registration`() = runTest {

        // Given
        coEvery {
            insertUserOnDBUseCase.insertNewRecord(any(), any())
        } coAnswers { BaseResult.Success(successMessage) }

        // When
        viewModel.registerNewClient()

        // then
        viewModel.state.test {
            assertEquals(viewModel.state.value, awaitItem())
            assertEquals(
                viewModel.state.value.copy(quantity = "", message = successMessage),
                awaitItem()
            )
        }
    }

    @Test
    fun `When roomPosition is -1 state only update day and dayPosition`() = runTest {

        // Given

        // When

        // Then
        viewModel.state.test {
            assertEquals(viewModel.state.value, awaitItem())

            viewModel.onSelectedDay(5)

            assertEquals(viewModel.state.value.copy(dayPosition = 5), awaitItem())
        }
    }

    @Test
    fun `When roomPosition is different to -1, day, dayPosition, filledSeats, availableSeats are update`() =
        runTest {

            // Given
            every { getFilledSeatsUseCase.getFilledSeats(any(), any(), any()) } returns 4

            // When
            viewModel.onSelectedRoom(4)

            // Then
            viewModel.state.test {
                assertEquals(viewModel.state.value, awaitItem())

                viewModel.onSelectedDay(5)

                assertEquals(
                    viewModel.state.value.copy(
                        roomPosition = 4,
                        dayPosition = 5,
                        filledSeats = 4,
                        availableSeats = 56
                    ),
                    awaitItem()
                )
            }
        }
}