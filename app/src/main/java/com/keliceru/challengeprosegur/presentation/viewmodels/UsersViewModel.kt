package com.keliceru.challengeprosegur.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keliceru.challengeprosegur.domain.entitites.Room
import com.keliceru.challengeprosegur.domain.entitites.User
import com.keliceru.challengeprosegur.domain.usecases.DeleteUserUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRegisteredUsersUseCase
import com.keliceru.challengeprosegur.domain.usecases.GetRoomsUseCase
import com.keliceru.challengeprosegur.domain.usecases.UpdateRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getRegisteredUsersUseCase: GetRegisteredUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateRoomUseCase: UpdateRoomUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,

    ) :
    ViewModel() {

    private val mutableState = MutableStateFlow(UsersState())
    val state = mutableState.asStateFlow()

    fun getInformationFromDB() {
        viewModelScope.launch {
            launch {
                getRegisteredUsersUseCase.getRegisteredUsers().collect { users ->
                    mutableState.update {
                        it.copy(users = users, empty = users.isEmpty())
                    }
                }
            }

            launch {
                getRoomsUseCase.getRooms().collect { rooms ->
                    mutableState.update {
                        it.copy(rooms = rooms)
                    }
                }
            }
        }
    }

    val discardUser: (User) -> Unit = { user ->

        viewModelScope.launch {

            val room = state.value.rooms.first { it.roomNumber == user.roomNumber }

            deleteUserUseCase.deleteUser(user.id.toInt())

            updateRoomUseCase.updateSeatsOnRoom(room, user.dayPosition, user.quantity)

            mutableState.update {
                it.copy(empty = state.value.users.isEmpty())
            }
        }
    }
}

data class UsersState(
    val users: List<User> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val empty: Boolean = true,
)
