package com.javichaques.feature.users.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.javichaques.core.domain.usecase.users.GetUsersUseCase
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
            refresh()
        }

        fun refresh() =
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        users = getUsers(),
                    )
                }
            }

        private suspend fun getUsers(): Flow<PagingData<UserDO>> {
            return getUsersUseCase()
                .cachedIn(viewModelScope)
        }

        fun onMoreOptionsClick() {
            // TODO To be implemented
        }
    }

data class UsersListUiState(
    val error: RUError? = null,
    val users: Flow<PagingData<UserDO>> = emptyFlow(),
)

sealed interface UsersListUiEvent
