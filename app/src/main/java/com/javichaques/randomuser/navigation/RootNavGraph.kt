package com.javichaques.randomuser.navigation

import com.javichaques.feature.users.ui.list.UsersNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object RootNavGraph : NavGraphSpec {
    override val route = "root"
    override val startRoute = UsersNavGraph
    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
    override val nestedNavGraphs =
        listOf(
            UsersNavGraph,
        )
}
