package com.javichaques.feature.users.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.component.RUTopAppBar
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.theme.SfProText
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.Mocks
import com.javichaques.core.model.UserDO
import com.javichaques.core.ui.scaffold.RUScaffold
import com.javichaques.feature.users.navigation.UsersNavGraph
import com.javichaques.feature.users.navigation.UsersNavigator
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@UsersNavGraph(start = true)
@Destination
@Composable
internal fun UsersListScreen(navigator: UsersNavigator) {
    val viewModel: UsersListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RUScaffold(
        error = state.error,
    ) {
        RUTopAppBar(
            title = stringResource(id = R.string.contacts),
            onNavigationClick = navigator::navigateUp,
            actions = {
                IconButton(
                    onClick = viewModel::onMoreOptionsClick,
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            contentColor = RUColor.Primary.Black,
                            containerColor = RUColor.Primary.White,
                        ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dots),
                        contentDescription = null,
                    )
                }
            },
        )

        UsersListScreenContent(
            state = state,
        )
    }
}

@Composable
internal fun UsersListScreenContent(
    state: UsersListUiState,
    onUserClick: (user: UserDO) -> Unit = {},
) {
    val users: LazyPagingItems<UserDO> = state.users.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = users.itemCount,
            key = users.itemKey { it.email },
        ) {
            users[it]?.let { user ->
                UserItem(
                    user = user,
                    onUserClick = onUserClick,
                )

                HorizontalDivider(
                    color = RUColor.Grey.Light,
                    thickness = 1.dp,
                    modifier =
                        Modifier.padding(
                            start = 84.dp,
                        ),
                )
            }
        }
    }
}

@Composable
internal fun UserItem(
    user: UserDO,
    onUserClick: (user: UserDO) -> Unit = {},
) {
    Button(
        onClick = { onUserClick(user) },
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(0.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = RUColor.Primary.White,
                contentColor = RUColor.Primary.Black,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.img_user_placeholder),
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(52.dp)
                        .clip(CircleShape),
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = user.name.getFullName(),
                    color = RUColor.Primary.Black,
                    fontFamily = SfProText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 21.sp,
                )

                Text(
                    text = user.email,
                    color = RUColor.Grey.Email,
                    fontFamily = SfProText,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                )
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

@DevicePreviews
@Composable
internal fun UserItemPreview() {
    RUTheme {
        UserItem(user = Mocks.user)
    }
}
