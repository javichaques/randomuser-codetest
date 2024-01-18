package com.javichaques.feature.users.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import com.javichaques.core.domain.usecase.users.GetUsersUseCase
import com.javichaques.core.model.Gender
import com.javichaques.core.model.UserDO
import com.javichaques.core.model.errors.RUError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel
    @Inject
    constructor(
        private val getUsersUseCase: GetUsersUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(UsersListUiState())
        val uiState: StateFlow<UsersListUiState> = _uiState.asStateFlow()

        private val _uiEvent = MutableSharedFlow<UsersListUiEvent>()
        val uiEvent = _uiEvent.asSharedFlow()

        init {
            getUsers()
        }

        private fun getUsers() =
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        users = getUsersUseCase(it.selectedGender),
                        filteredUsers = emptyFlow(),
                    )
                }

                filter()
            }

        fun onUserClick(user: UserDO) =
            viewModelScope.launch {
                _uiEvent.emit(
                    UsersListUiEvent.NavigateToUserDetail(
                        user = user,
                    ),
                )
            }

        fun onMoreOptionsClick() {
            // TODO To be implemented
        }

        fun filterUsersByEmail(value: String) {
            _uiState.update {
                it.copy(
                    query = value,
                )
            }

            filter()
        }

        private fun filter() {
            _uiState.update {
                val filteredUsers =
                    it.users.mapLatest { pagingData ->
                        pagingData.filter { user ->
                            user.email.contains(it.query)
                        }
                    }

                it.copy(
                    filteredUsers = filteredUsers,
                )
            }
        }

        fun filterUsersByGender(gender: Gender?) {
            _uiState.update {
                it.copy(
                    selectedGender = gender,
                )
            }

            getUsers()
        }

        fun clearQuery() {
            _uiState.update {
                it.copy(
                    query = "",
                )
            }

            filter()
        }
    }

data class UsersListUiState(
    val error: RUError? = null,
    val query: String = "",
    val users: Flow<PagingData<UserDO>> = emptyFlow(),
    val filteredUsers: Flow<PagingData<UserDO>> = emptyFlow(),
    val selectedGender: Gender? = null,
)

sealed interface UsersListUiEvent {
    data class NavigateToUserDetail(val user: UserDO) : UsersListUiEvent
}
