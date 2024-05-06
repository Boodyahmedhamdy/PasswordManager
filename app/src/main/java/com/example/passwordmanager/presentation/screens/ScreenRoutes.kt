package com.example.passwordmanager.presentation.screens

sealed class ScreenRoutes(
    val route: String,
    val hasTopBar: Boolean = true,
    val hasBottomBar: Boolean = true,
    val hasFabButton: Boolean = true,
    val title: String = "Screen"
) {
    data object HomeScreen: ScreenRoutes(
        "homeScreen",
        title = "Home"
    )
    data object SecurityGateScreen: ScreenRoutes("securityScreen")
    data object PasswordPreviewScreen: ScreenRoutes(
        "passwordPreviewScreen",
        hasTopBar = false,
        hasBottomBar = false,
        hasFabButton = false
    )
    data object PasswordConfigurationScreen: ScreenRoutes(
        "passwordConfigurationScreen",
        hasTopBar = false,
        hasBottomBar = false,
        hasFabButton = false
    )
    data object PasswordDetailsScreen: ScreenRoutes(
        "passwordDetailsScreen"
    )
    data object PasswordEditScreen: ScreenRoutes("passwordEditScreen")

}