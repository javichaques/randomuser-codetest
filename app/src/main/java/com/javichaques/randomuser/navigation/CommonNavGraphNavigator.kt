package com.javichaques.randomuser.navigation

import androidx.navigation.NavController
import com.javichaques.feature.users.navigation.UsersNavigator

class CommonNavGraphNavigator(
    private val navController: NavController,
) : UsersNavigator {
    override fun navigateUp() {
        navController.navigateUp()
    }
}
