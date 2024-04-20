package com.example.passwordmanager.presentation.screens

sealed class ScreenRoutes(val route: String) {
    data object HomeScreen: ScreenRoutes("homeScreen")
    data object SecurityGateScreen: ScreenRoutes("securityScreen")
    data object PasswordPreviewScreen: ScreenRoutes("passwordPreviewScreen")
    data object PasswordConfigurationScreen: ScreenRoutes("passwordConfigurationScreen")
    data object PasswordDetailsScreen: ScreenRoutes("passwordDetailsScreen")
    data object PasswordEditScreen: ScreenRoutes("passwordEditScreen")
}