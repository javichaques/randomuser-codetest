package com.javichaques.randomuser.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun rememberRUAppState(
    engine: NavHostEngine = rememberAnimatedNavHostEngine(),
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
