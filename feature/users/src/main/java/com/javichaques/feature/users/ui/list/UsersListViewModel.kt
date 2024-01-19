package com.javichaques.feature.users.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
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

        private val queryFlow = MutableStateFlow("")

        init {
            getUsers()
        }

        private fun getUsers() =
            viewModelScope.launch {
                _uiState.update {
                    val users =
                        getUsersUseCase(it.selectedGender)
                            .cachedIn(viewModelScope)
                            .combine(queryFlow) { pagingData, query ->
                                pagingData.filter { user ->
                                    user.email.contains(query)
                                }
                            }

                    it.copy(
                        users = users,
                    )
                }
            }

        fun onUserClick(user: UserDO) =
            viewModelScope.launch {
                _uiEvent.emit(
                    UsersListUiEvent.NavigateToUserDetail(
                        user = user,
                    ),
                )
            }

        fun onMoreOptionsClick() =
            viewModelScope.launch {
                _uiEvent.emit(
                    UsersListUiEvent.NavigateToMoreOptions,
                )
            }

        fun filterUsersByEmail(value: String) {
            _uiState.update {
                it.copy(
                    query = value,
                )
            }
            queryFlow.update { value }
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
            filterUsersByEmail("")
        }

        fun onRetry(error: RUError) {
            _uiState.update {
                it.copy(error = null)
            }
            getUsers()
        }
    }

data class UsersListUiState(
    val error: RUError? = null,
    val query: String = "",
    val users: Flow<PagingData<UserDO>> = emptyFlow(),
    val selectedGender: Gender? = null,
)

sealed interface UsersListUiEvent {
    data class NavigateToUserDetail(val user: UserDO) : UsersListUiEvent

    data object NavigateToMoreOptions : UsersListUiEvent
}
