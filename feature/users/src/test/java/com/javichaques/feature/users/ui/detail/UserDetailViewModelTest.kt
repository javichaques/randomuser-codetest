package com.javichaques.feature.users.ui.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.javichaques.core.model.Mocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {
    private lateinit var viewModel: UserDetailViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)

        viewModel =
            UserDetailViewModel(
                savedStateHandle =
                    SavedStateHandle(
                        mapOf("user" to Mocks.user),
                    ),
            )
    }

    @DisplayName("Test ui state when set user")
    @Test
    fun checkSetUser_success() =
        runTest {
            val user = Mocks.user

            val expectedUiState =
                UserDetailUiState(
                    toolbarTitle = user.name.getFullName(),
                    userImage = user.picture.large,
                    name = user.name.getFullName(),
                    mail = user.email,
                    gender = user.gender,
                    registeredDate = user.registered.date,
                    phone = user.phone,
                    coordinates = user.location.coordinates,
                )

            viewModel.setUser(
                user = user,
            )

            val state = viewModel.uiState.value

            assertEquals(expectedUiState, state)
        }

    @DisplayName("Test on more options click")
    @Test
    fun checkOnMoreOptionsClick() =
        runTest {
            viewModel.onMoreOptionsClick()

            viewModel.uiEvent.test {
                assertInstanceOf(UserDetailUiEvent.NavigateToMoreOptions::class.java, awaitItem())
            }
        }
}
