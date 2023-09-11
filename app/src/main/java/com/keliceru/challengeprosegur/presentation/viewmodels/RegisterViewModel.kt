package com.keliceru.challengeprosegur.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keliceru.challengeprosegur.domain.common.BaseResult
import com.keliceru.challengeprosegur.domain.common.totalSeats
import com.keliceru.challengeprosegur.domain.common.weekDays
import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.usecases.GetFilledSeatsUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRoomsUseCase
import com.keliceru.challengeprosegur.domain.usecases.InsertUserOnDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val insertUserOnDBUseCase: InsertUserOnDBUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getFilledSeatsUseCase: GetFilledSeatsUseCase,
) :
    ViewModel() {

    private val mutableState = MutableStateFlow(RegisterState())
    val state = mutableState.asStateFlow()

    fun registerNewClient() {
        viewModelScope.launch {
            val response = insertUserOnDBUseCase.insertNewRecord(
                User(
                    roomNumber = state.value.roomNumber,
                    day = state.value.day,
                    dayPosition = state.value.dayPosition,
                    movie = state.value.movie,
                    price = state.value.price,
                    quantity = if (state.value.quantity.isBlank()) 0 else state.value.quantity.toInt(),
                    dni = state.value.dni,
                    onError = state.value.error
                ), state.value.filledSeats
            )

            when (response) {
                is BaseResult.Error -> {
                    mutableState.update {
                        it.copy(message = response.rawResponse)
                    }
                }

                is BaseResult.Success -> {
                    mutableState.update {
                        it.copy(quantity = "", message = response.data)
                    }

                    delay(3000)

                    mutableState.update {
                        it.copy(message = "")
                    }
                }
            }
        }
    }

    val onSelectedDay: (Int) -> Unit = { i ->

        if (state.value.roomPosition == -1) {
            mutableState.update {
                it.copy(dayPosition = i, quantity = "")
            }
        } else {
            mutableState.update {
                it.copy(
                    dayPosition = i,
                    filledSeats = getFilledSeatsUseCase.getFilledSeats(
                        i,
                        state.value.roomPosition,
                        state.value.rooms
                    ),
                    availableSeats = totalSeats - getFilledSeatsUseCase.getFilledSeats(
                        i,
                        state.value.roomPosition,
                        state.value.rooms
                    ),
                    showTopMessage = true,
                    quantity = ""
                )
            }
        }
    }

    val onSelectedRoom: (Int) -> Unit = { i ->

        val day = state.value.dayPosition

        if (day == -1) {
            mutableState.update {
                it.copy(roomPosition = i, quantity = "")
            }
        } else {
            mutableState.update {
                it.copy(
                    roomPosition = i,
                    filledSeats = getFilledSeatsUseCase.getFilledSeats(day, i, state.value.rooms),
                    availableSeats = totalSeats - getFilledSeatsUseCase.getFilledSeats(
                        day,
                        i,
                        state.value.rooms
                    ),
                    showTopMessage = true,
                    quantity = ""
                )
            }
        }
    }

    val onError: (String) -> Unit = {
        mutableState.update {
            it.copy(error = isRegisterOnError())
        }
    }

    fun getCinemaRooms() {

        viewModelScope.launch {
            getRoomsUseCase.getRooms().collect { rooms ->
                mutableState.update {
                    it.copy(
                        roomsNumber = rooms.map { it.roomNumber },
                        rooms = rooms
                    )
                }

                if (state.value.dayPosition != -1 && state.value.roomPosition != -1)

                    mutableState.update {
                        it.copy(
                            filledSeats = getFilledSeatsUseCase.getFilledSeats(
                                state.value.dayPosition,
                                state.value.roomPosition,
                                state.value.rooms
                            ),
                            availableSeats = totalSeats - getFilledSeatsUseCase.getFilledSeats(
                                state.value.dayPosition,
                                state.value.roomPosition,
                                state.value.rooms
                            ),
                        )
                    }
            }
        }
    }

    private fun isRegisterOnError(): Boolean {
        return state.value.roomNumber.isBlank() || state.value.day.isBlank()
                || state.value.movie.isBlank() || state.value.price.isBlank()
                || state.value.quantity.isBlank() || state.value.quantity.toInt() == 0
                || state.value.quantity.toInt() > state.value.availableSeats
                || state.value.dni.isBlank()
    }
}

data class RegisterState(
    var roomNumber: String = "",
    val roomPosition: Int = -1,
    var day: String = "",
    val dayPosition: Int = -1,
    var movie: String = "",
    var price: String = "",
    var quantity: String = "",
    var dni: String = "",
    var message: String = "",
    var showTopMessage: Boolean = false,
    val roomsNumber: List<String> = listOf(),
    val rooms: List<Room> = emptyList(),
    val days: List<String> = weekDays,
    val availableSeats: Int = 0,
    val filledSeats: Int = 0,
    val error: Boolean = true,
)



