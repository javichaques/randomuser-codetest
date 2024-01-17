package com.javichaques.randomuser.navigation

import androidx.navigation.NavController
import com.javichaques.core.model.UserDO
import com.javichaques.feature.users.navigation.UsersNavigator
import com.javichaques.feature.users.ui.destinations.UserDetailScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class CommonNavGraphNavigator(
    private val navController: NavController,
) : UsersNavigator {
    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToUserDetail(user: UserDO) {
        navController.navigate(
            UserDetailScreenDestination(
                user = user,
            ),
        )
    }
}
