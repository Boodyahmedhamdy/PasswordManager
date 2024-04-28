package com.example.passwordmanager.presentation.screens

sealed class ScreenRoutes(
    val route: String,
    val hasTopBar: Boolean = true,
    val hasBottomBar: Boolean = true,
    val hasFabButton: Boolean = true
) {
    data object HomeScreen: ScreenRoutes("homeScreen")
    data object SecurityGateScreen: ScreenRoutes("securityScreen")
    data object PasswordPreviewScreen: ScreenRoutes("passwordPreviewScreen")
    data object PasswordConfigurationScreen: ScreenRoutes("passwordConfigurationScreen")
    data object PasswordDetailsScreen: ScreenRoutes("passwordDetailsScreen")
    data object PasswordEditScreen: ScreenRoutes("passwordEditScreen")

}