@file:OptIn(ExperimentalCoroutinesApi::class)

package com.keliceru.challengeprosegur.viewmodels

import app.cash.turbine.test
import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.usecases.DeleteUserUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRegisteredUsersUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRoomsUseCase
import com.keliceru.challengeprosegur.domain.usecases.UpdateRoomUseCase
import com.keliceru.challengeprosegur.presentation.viewmodels.UsersViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersViewModelUnitTest {


    private lateinit var usersViewModel: UsersViewModel
    private lateinit var getRegisteredUsersUseCase: GetRegisteredUsersUseCase
    private lateinit var deleteUserUseCase: DeleteUserUseCase
    private lateinit var updateRoomUseCase: UpdateRoomUseCase
    private lateinit var getRoomsUseCase: GetRoomsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        getRegisteredUsersUseCase = mockk()
        deleteUserUseCase = mockk()
        updateRoomUseCase = mockk()
        getRoomsUseCase = mockk()
        usersViewModel = UsersViewModel(
            getRegisteredUsersUseCase,
            deleteUserUseCase,
            updateRoomUseCase,
            getRoomsUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get success information from database`() = runTest {

        // Given
        val users = listOf<User>(mockk(), mockk())
        val rooms = listOf<Room>(mockk(), mockk())

        every { getRegisteredUsersUseCase.getRegisteredUsers() } returns flowOf(users)
        every { getRoomsUseCase.getRooms() } returns flowOf(rooms)

        // When
        usersViewModel.getInformationFromDB()

        usersViewModel.state.test {
            assertEquals(usersViewModel.state.value, awaitItem())
            assertEquals(
                usersViewModel.state.value.copy(users = users, empty = false),
                awaitItem()
            )
            assertEquals(
                usersViewModel.state.value.copy(users = users, empty = false, rooms = rooms),
                awaitItem()
            )
        }
    }

    @Test
    fun `User has been discarded leaving empty users list`() = runTest {
        // Given

        val user = User(
            roomNumber = "201",
            day = "Lunes",
            dayPosition = 0,
            movie = "movie",
            price = "10",
            quantity = 5,
            dni = "19654456"
        )
        val users = listOf<User>(user)
        val rooms = listOf<Room>(
            Room(
                roomNumber = "201",
                maxCapacity = 60,
                seatsFilledMonday = 0,
                seatsFilledTuesday = 0,
                seatsFilledWednesday = 0,
                seatsFilledThursday = 0,
                seatsFilledFriday = 0,
                seatsFilledSaturday = 0,
                seatsFilledSunday = 0
            )
        )
        val usersFlow = MutableStateFlow<List<User>>(users)

        every { getRegisteredUsersUseCase.getRegisteredUsers() } returns usersFlow
        every { getRoomsUseCase.getRooms() } returns flowOf(rooms)
        coEvery { deleteUserUseCase.deleteUser(any()) } returns Unit
        coEvery { updateRoomUseCase.updateSeatsOnRoom(any(), any(), any()) } returns Unit

        // When
        usersViewModel.getInformationFromDB()

        // Then
        usersViewModel.state.test {

            assertEquals(usersViewModel.state.value, awaitItem())
            assertEquals(usersViewModel.state.value.copy(users = users, empty = false), awaitItem())
            assertEquals(
                usersViewModel.state.value.copy(
                    users = users,
                    empty = false,
                    rooms = rooms
                ), awaitItem()
            )
            usersViewModel.discardUser(user)

            usersFlow.update { emptyList() }

            advanceUntilIdle()

            assertEquals(
                usersViewModel.state.value.copy(
                    users = emptyList(),
                    empty = true
                ), awaitItem()
            )
        }
    }

    @Test
    fun `User has been discarded leaving not empty users list`() = runTest {
        // Given

        val user1 = User(
            roomNumber = "201",
            day = "Lunes",
            dayPosition = 0,
            movie = "movie",
            price = "10",
            quantity = 5,
            dni = "19654456"
        )
        val user2 = User(
            roomNumber = "201",
            day = "Lunes",
            dayPosition = 0,
            movie = "movie",
            price = "10",
            quantity = 5,
            dni = "19657776"
        )
        val users = listOf<User>(user1, user2)
        val rooms = listOf<Room>(
            Room(
                roomNumber = "201",
                maxCapacity = 60,
                seatsFilledMonday = 0,
                seatsFilledTuesday = 0,
                seatsFilledWednesday = 0,
                seatsFilledThursday = 0,
                seatsFilledFriday = 0,
                seatsFilledSaturday = 0,
                seatsFilledSunday = 0
            )
        )
        val usersFlow = MutableStateFlow<List<User>>(users)

        every { getRegisteredUsersUseCase.getRegisteredUsers() } returns usersFlow
        every { getRoomsUseCase.getRooms() } returns flowOf(rooms)
        coEvery { deleteUserUseCase.deleteUser(any()) } returns Unit
        coEvery { updateRoomUseCase.updateSeatsOnRoom(any(), any(), any()) } returns Unit

        // When
        usersViewModel.getInformationFromDB()

        // Then
        usersViewModel.state.test {

            assertEquals(usersViewModel.state.value, awaitItem())
            assertEquals(usersViewModel.state.value.copy(users = users, empty = false), awaitItem())
            assertEquals(
                usersViewModel.state.value.copy(
                    users = users,
                    empty = false,
                    rooms = rooms
                ), awaitItem()
            )
            usersViewModel.discardUser(user1)

            usersFlow.update { listOf(user2) }

            advanceUntilIdle()

            assertEquals(
                usersViewModel.state.value.copy(
                    users = listOf(user2),
                    empty = false
                ), awaitItem()
            )
        }
    }
}