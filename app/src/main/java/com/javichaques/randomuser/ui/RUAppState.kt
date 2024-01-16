package com.javichaques.randomuser.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun rememberRUAppState(
    engine: NavHostEngine = rememberNavHostEngine(),
    navController: NavHostController = engine.rememberNavController(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
): RUAppState {
    return remember(engine, navController, snackBarHostState) {
        RUAppState(engine, navController, snackBarHostState)
    }
}

@Stable
class RUAppState(
    val engine: NavHostEngine,
    val navController: NavHostController,
    val snackBarHostState: SnackbarHostState,
)
