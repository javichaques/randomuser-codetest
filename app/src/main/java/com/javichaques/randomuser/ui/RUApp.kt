@file:OptIn(ExperimentalMaterial3Api::class)

package com.javichaques.randomuser.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.javichaques.core.designsystem.theme.RUColor
import com.javichaques.randomuser.navigation.RootNavGraph
import com.javichaques.randomuser.ui.utils.currentNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun RUApp(appState: RUAppState = rememberRUAppState()) {
    Scaffold(
        modifier =
            Modifier
                .fillMaxSize()
                .imePadding(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = RUColor.Primary.White,
        contentColor = RUColor.Primary.Black,
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackBarHostState,
                modifier = Modifier.navigationBarsPadding(),
            ) {
                Snackbar(snackbarData = it)
            }
        },
    ) { padding ->
        DestinationsNavHost(
            engine = appState.engine,
            navController = appState.navController,
            navGraph = RootNavGraph,
            startRoute = RootNavGraph.startRoute,
            dependenciesContainerBuilder = {
                dependency(currentNavigator())
                dependency(appState.snackBarHostState)
            },
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
        )
    }
}
