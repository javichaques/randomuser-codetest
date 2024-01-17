package com.javichaques.feature.users.navigation

import com.javichaques.core.model.UserDO

interface UsersNavigator {
    fun navigateUp()

    fun navigateToUserDetail(user: UserDO)
}
