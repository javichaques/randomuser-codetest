package com.javichaques.randomuser.ui.utils

import com.javichaques.randomuser.navigation.CommonNavGraphNavigator
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder

fun DependenciesContainerBuilder<*>.currentNavigator(): CommonNavGraphNavigator {
    return CommonNavGraphNavigator(
        navController,
    )
}
