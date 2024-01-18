@file:OptIn(ExperimentalMaterial3Api::class)

package com.javichaques.feature.users.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.javichaques.core.designsystem.R
import com.javichaques.core.designsystem.component.BackgroundType
import com.javichaques.core.designsystem.component.RUTopAppBar
import com.javichaques.core.designsystem.theme.OpenSans
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.core.designsystem.theme.RUTheme
import com.javichaques.core.designsystem.util.DevicePreviews
import com.javichaques.core.model.Gender
import com.javichaques.core.model.UserDO
import com.javichaques.core.ui.transitions.DetailTransitions
import com.javichaques.core.ui.utils.DateTimeFormat
import com.javichaques.core.ui.utils.toString
import com.javichaques.feature.users.navigation.UsersNavGraph
import com.javichaques.feature.users.navigation.UsersNavigator
import com.ramcosta.composedestinations.annotation.Destination
import java.time.ZonedDateTime

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
        val (background, toolbar, image, data, actions) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_user_background),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .constrainAs(background) {
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

        UserActions(
            modifier =
                Modifier.constrainAs(actions) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(background.bottom)
                    width = Dimension.fillToConstraints
                },
        )

        UserDetailData(
            state = state,
            modifier =
                Modifier.constrainAs(data) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(actions.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
        )

        AsyncImage(
            model = state.userImage,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.img_user_placeholder),
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .padding(start = 16.dp)
                    .size(77.dp)
                    .clip(CircleShape)
                    .border(2.dp, RUColor.Primary.White, CircleShape)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(background.bottom)
                        bottom.linkTo(background.bottom)
                    },
        )
    }
}

@Composable
internal fun UserDetailData(
    state: UserDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier.verticalScroll(
                rememberScrollState(),
            ),
    ) {
        if (state.name.isNotBlank()) {
            DetailItem(
                title = stringResource(id = R.string.name_and_surname),
                body = state.name,
                icon = painterResource(id = R.drawable.ic_user),
            )
        }

        if (state.mail.isNotBlank()) {
            DetailItem(
                title = stringResource(id = R.string.mail),
                body = state.mail,
                icon = painterResource(id = R.drawable.ic_mail),
            )
        }

        if (state.gender != null && state.gender != Gender.Unknown) {
            val gender =
                when (state.gender) {
                    Gender.Male -> stringResource(id = R.string.gender_male)
                    Gender.Female -> stringResource(id = R.string.gender_female)
                    else -> ""
                }
            DetailItem(
                title = stringResource(id = R.string.gender),
                body = gender,
                icon = painterResource(id = R.drawable.ic_gender),
            )
        }

        if (state.registeredDate != null) {
            DetailItem(
                title = stringResource(id = R.string.registered_date),
                body = state.registeredDate.toString(DateTimeFormat.DayMonthYearShortDate),
                icon = painterResource(id = R.drawable.ic_calendar),
            )
        }

        if (state.phone.isNotBlank()) {
            DetailItem(
                title = stringResource(id = R.string.phone),
                body = state.phone,
                icon = painterResource(id = R.drawable.ic_phone),
            )
        }
    }
}

@Composable
internal fun DetailItem(
    title: String,
    body: String,
    icon: Painter,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        contentPadding =
            PaddingValues(
                vertical = 8.dp,
                horizontal = 20.dp,
            ),
        shape = RoundedCornerShape(0.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = RUColor.Primary.White,
                contentColor = RUColor.Primary.Black,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(32.dp),
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = title,
                        color = RUColor.Grey.Dim,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        lineHeight = 14.sp,
                    )

                    Text(
                        text = body,
                        color = RUColor.Primary.Black,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                    )
                }
            }

            HorizontalDivider(
                color = RUColor.Grey.Light,
                thickness = 1.dp,
                modifier =
                    Modifier.padding(
                        top = 8.dp,
                        start = 52.dp,
                    ),
            )
        }
    }
}

@Composable
internal fun UserActions(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier,
    ) {
        IconButton(onClick = onCameraClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
            )
        }

        IconButton(onClick = onEditClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = null,
            )
        }
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
                    name = "Javi Chaqués",
                    mail = "javichaques@gmail.com",
                    gender = Gender.Male,
                    registeredDate = ZonedDateTime.now(),
                    phone = "+34 666 666 666",
                ),
        )
    }
}

@DevicePreviews
@Composable
internal fun DetailItemPreview() {
    RUTheme {
        DetailItem(
            title = stringResource(id = R.string.name_and_surname),
            body = "Javi Chaqués",
            icon = painterResource(id = R.drawable.ic_user),
        )
    }
}

@DevicePreviews
@Composable
internal fun UserActionsPreview() {
    RUTheme {
        UserActions()
    }
}
