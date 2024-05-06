package com.example.passwordmanager.presentation.utils

import com.example.passwordmanager.presentation.screens.ScreenRoutes

fun getScreenFromRoute(route: String): ScreenRoutes {
    return when (route) {
        ScreenRoutes.HomeScreen.route -> ScreenRoutes.HomeScreen
        ScreenRoutes.PasswordConfigurationScreen.route -> ScreenRoutes.PasswordConfigurationScreen
        ScreenRoutes.PasswordPreviewScreen.route -> ScreenRoutes.PasswordPreviewScreen
        ScreenRoutes.PasswordDetailsScreen.route ->  ScreenRoutes.PasswordDetailsScreen
        ScreenRoutes.PasswordEditScreen.route -> ScreenRoutes.PasswordEditScreen
        else -> ScreenRoutes.HomeScreen
    }
}