@file:OptIn(ExperimentalMaterial3Api::class)

package com.javichaques.feature.users.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.component.BackgroundType
import com.javichaques.core.designsystem.component.RUTopAppBar
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.UserDO
import com.javichaques.core.ui.transitions.DetailTransitions
import com.javichaques.feature.users.navigation.UsersNavGraph
import com.javichaques.feature.users.navigation.UsersNavigator
import com.ramcosta.composedestinations.annotation.Destination

data class UserDetailScreenArgs(
    val user: UserDO,
)

@UsersNavGraph
@Destination(
    style = DetailTransitions::class,
    navArgsDelegate = UserDetailScreenArgs::class,
)
@Composable
internal fun UserDetailScreen(navigator: UsersNavigator) {
    val viewModel: UserDetailViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    UserDetailScreenContent(
        state = state,
        onNavigateUp = navigator::navigateUp,
        onMoreOptionsClick = viewModel::onMoreOptionsClick,
    )
}

@Composable
internal fun UserDetailScreenContent(
    state: UserDetailUiState,
    onNavigateUp: () -> Unit = {},
    onMoreOptionsClick: () -> Unit = {},
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, toolbar) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_user_background),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    },
        )

        RUTopAppBar(
            title = state.toolbarTitle,
            onNavigationClick = onNavigateUp,
            backgroundType = BackgroundType.Transparent,
            actions = {
                IconButton(
                    onClick = onMoreOptionsClick,
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            contentColor = RUColor.Primary.White,
                            containerColor = Color.Transparent,
                        ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dots),
                        contentDescription = null,
                    )
                }
            },
            modifier =
                Modifier.constrainAs(toolbar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
        )
    }
}

@DevicePreviews
@Composable
internal fun UserDetailScreenContentPreview() {
    RUTheme {
        UserDetailScreenContent(
            state =
                UserDetailUiState(
                    toolbarTitle = "Javi Chaqués",
                ),
        )
    }
}
