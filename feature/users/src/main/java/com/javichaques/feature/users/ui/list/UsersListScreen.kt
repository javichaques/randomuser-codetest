package com.javichaques.feature.users.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.UserDO
import com.javichaques.core.ui.paging.pagingList
import com.javichaques.core.ui.scaffold.RUScaffold
import com.javichaques.feature.users.navigation.UsersNavGraph
import com.javichaques.feature.users.navigation.UsersNavigator
import com.ramcosta.composedestinations.annotation.Destination

@UsersNavGraph(start = true)
@Destination
@Composable
internal fun UsersListScreen(navigator: UsersNavigator) {
    val viewModel: UsersListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RUScaffold(
        error = state.error,
    ) {
        UsersListScreenContent(
            state = state,
        )
    }
}

@Composable
internal fun UsersListScreenContent(state: UsersListUiState) {
    val users: LazyPagingItems<UserDO> = state.users.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Text(text = "Javi")
        }

        pagingList<UserDO>(
            items = users,
        ) {
            items(users.itemCount) {
                val user = users[it]
                if (user != null) {
                    Text(text = user.name.first + "Hola")
                }
            }
        }
    }
}

@DevicePreviews
@Composable
internal fun UsersListScreenContentPreview() {
    RUTheme {
        UsersListScreenContent(state = UsersListUiState())
    }
}
