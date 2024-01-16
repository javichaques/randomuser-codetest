package com.javichaques.feature.users.ui.list

import androidx.compose.runtime.Composable
import com.javichaques.feature.users.navigation.UsersNavGraph
import com.javichaques.feature.users.navigation.UsersNavigator
import com.ramcosta.composedestinations.annotation.Destination

@UsersNavGraph(start = true)
@Destination
@Composable
internal fun WelcomeScreen(navigator: UsersNavigator) {
}
