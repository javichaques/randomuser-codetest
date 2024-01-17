package com.javichaques.feature.users.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.javichaques.core.model.UserDO
import com.javichaques.feature.users.ui.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(UserDetailUiState())
        val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

        private val _uiEvent = MutableSharedFlow<UserDetailUiEvent>()
        val uiEvent = _uiEvent.asSharedFlow()

        private val user = checkNotNull(savedStateHandle.navArgs<UserDetailScreenArgs>().user)

        init {
            setUser(user)
        }

        private fun setUser(user: UserDO) {
            _uiState.update {
                it.copy(
                    toolbarTitle = user.name.getFullName(),
                )
            }
        }

        fun onMoreOptionsClick() {
            // TODO To be implemented
        }
    }

data class UserDetailUiState(
    val toolbarTitle: String = "",
)

sealed interface UserDetailUiEvent
