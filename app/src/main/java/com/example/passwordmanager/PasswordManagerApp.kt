package com.example.passwordmanager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.passwordmanager.presentation.components.BottomBarNavigation
import com.example.passwordmanager.presentation.components.TabBarItem
import com.example.passwordmanager.presentation.screens.HomeScreen
import com.example.passwordmanager.presentation.screens.PasswordConfigurationScreen
import com.example.passwordmanager.presentation.screens.PasswordDetailsScreen
import com.example.passwordmanager.presentation.screens.PasswordEditScreen
import com.example.passwordmanager.presentation.screens.PasswordPreviewScreen
import com.example.passwordmanager.presentation.screens.PasswordRatingScreen
import com.example.passwordmanager.presentation.screens.ScreenRoutes
import com.example.passwordmanager.presentation.screens.SecurityGateScreen
import com.example.passwordmanager.presentation.screens.SettingsScreen
import com.example.passwordmanager.presentation.utils.getScreenFromRoute
import com.example.passwordmanager.presentation.viewmodels.EditPasswordViewModel
import com.example.passwordmanager.presentation.viewmodels.HomeScreenViewModel
import com.example.passwordmanager.presentation.viewmodels.PasswordGenerationViewModel
import com.example.passwordmanager.presentation.viewmodels.ScaffoldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerApp() {
    val viewmodel: HomeScreenViewModel = hiltViewModel()
    val state = viewmodel.state.collectAsState()

    val passwordGenerationViewModel: PasswordGenerationViewModel = hiltViewModel()
    val passwordGenerationState = passwordGenerationViewModel.state.collectAsState()

    val scaffoldViewModel: ScaffoldViewModel = hiltViewModel()



    val navController = rememberNavController()
    // to track current screen
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = getScreenFromRoute(backStackEntry?.destination?.route ?: "")

    val bottomBarItems = listOf(
        TabBarItem(
            "home", Icons.Filled.Home, Icons.Outlined.Home, onClick = {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
        ),
        TabBarItem(
            "Locks", Icons.Filled.RateReview, Icons.Outlined.RateReview, onClick = {
                navController.navigate(ScreenRoutes.PasswordRatingScreen.route)
            }
        ),
        TabBarItem(
            "home", Icons.Filled.Settings, Icons.Outlined.Settings, onClick = {
                navController.navigate(ScreenRoutes.SettingsScreen.route)
            }
        )
    )

    Scaffold(
        topBar = {
            AnimatedVisibility(visible = currentScreen.hasTopBar) {
                TopAppBar(
                    title = { Text(text = currentScreen.title) },
                    actions = {
                        IconButton(onClick = {
                            // function to delete goes here
                            scaffoldViewModel.deleteAllPasswords()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
            }},
        floatingActionButton = {
            AnimatedVisibility(visible = currentScreen.hasFabButton) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(ScreenRoutes.PasswordConfigurationScreen.route)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add new password")
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = currentScreen.hasBottomBar) {
                BottomBarNavigation(tabBarItem = bottomBarItems)
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeScreen.route
        ) {
            // home
            composable(
                ScreenRoutes.HomeScreen.route,
                enterTransition = {
                    slideInVertically(
                        tween(2000),
                        initialOffsetY = {
                            it / 2
                        }
                    ) + fadeIn(tween(2000))
                },
                exitTransition = {
                    slideOutVertically(
                        tween(2000)
                    ) + fadeOut(tween(2000))
                }
            ) {
                HomeScreen(
                    state = state.value,
                    onPasswordListItemClicked = {
                        navController.navigate(
                            route = "${ScreenRoutes.PasswordDetailsScreen.route}/${it}"
                        )
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize())
            }
            // security
            composable(ScreenRoutes.SecurityGateScreen.route) {
                SecurityGateScreen()
            }

            // rating
            composable(ScreenRoutes.PasswordRatingScreen.route) {
                PasswordRatingScreen(
                    modifier = Modifier.padding(paddingValues).fillMaxSize()
                )
            }

            // settings
            composable(ScreenRoutes.SettingsScreen.route) {
                SettingsScreen(
                    modifier = Modifier.padding(paddingValues).fillMaxSize()
                )
            }
            // details
            composable(
                route = "${ScreenRoutes.PasswordDetailsScreen.route}/{passwordId}",
                arguments = listOf(
                    navArgument("passwordId") { type = NavType.IntType }
                )
            ) {backStackEntry ->
                // TODO: LATE CREATION FOR VIEWMODEL
                val editPasswordViewModel: EditPasswordViewModel = hiltViewModel()
                val editPasswordUiState = editPasswordViewModel.state.collectAsState()

//                editPasswordViewModel.updatePasswordId(
//                    backStackEntry.arguments?.getInt("passwordId") ?: -1
//                )
                PasswordDetailsScreen(
                    state = editPasswordUiState.value.editPasswordScreenUiState.password,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }
            // edit
            composable(ScreenRoutes.PasswordEditScreen.route) {
                PasswordEditScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            // config
            composable(
                ScreenRoutes.PasswordConfigurationScreen.route,
                enterTransition = {
                    slideInVertically(
                        tween(2000),
                        initialOffsetY = {
                            it / 2
                        }
                    ) + fadeIn(tween(2000))
                },
                exitTransition = {
                    slideOutVertically(tween(2000)) + fadeOut(tween(2000))
                }
            ) {
                PasswordConfigurationScreen(
                    state = passwordGenerationState.value.passwordConfigurationScreenUiState,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    onConfigurationLengthChanged = {
                        // error when gives empty string or when values in text field is cleared
                        try {
                            passwordGenerationViewModel.updateLength(it.toInt())
                        } catch(e: Exception) {
                            passwordGenerationViewModel.updateLength(0)
                        }
                    },
                    onConfigurationHasNumbersChanged = {
                        passwordGenerationViewModel.updateHasNumbers(it)
                    },
                    onConfigurationCaseSensitiveChanged = {
                        passwordGenerationViewModel.updateCaseSensitive(it)
                    },
                    onConfigurationHasSpecialCharactersChanged = {
                        passwordGenerationViewModel.updateSpecialCharacters(it)
                    },
                    onClickGenerate = {
                        navController.navigate(ScreenRoutes.PasswordPreviewScreen.route)
                        passwordGenerationViewModel.generatePassword(it)
                    }
                )
            }

            // preview
            composable(
                ScreenRoutes.PasswordPreviewScreen.route,
                enterTransition = {
                    slideInVertically(
                        tween(2000),
                        initialOffsetY = {
                            it / 2
                        }
                    ) + fadeIn(tween(2000))
                },
                exitTransition = {
                    slideOutVertically(tween(2000)) + fadeOut(tween(2000))
                }
            ) {
                PasswordPreviewScreen(
                    state = passwordGenerationState.value.passwordPreviewScreenUiState,
                    modifier = Modifier.padding(paddingValues),
                    onClickReGenerate = {
                        passwordGenerationViewModel.generatePassword(
                            passwordGenerationState.value.passwordConfigurationScreenUiState.configuration
                        )
                    },
                    onClickSavePassword = {
                        passwordGenerationViewModel.showAlertDialog()
                    },
                    onDialogLabelValueChanged = {
                        passwordGenerationViewModel.updateGeneratedPasswordLabel(it)
                    },
                    onDialogDismissRequest = {
                        passwordGenerationViewModel.hideAlertDialog()
                    },
                    onDismissButtonClicked = {
                         passwordGenerationViewModel.hideAlertDialog()
                    },
                    onDialogConfirmationButtonClicked = {
                        passwordGenerationViewModel.savePassword()
                        navController.popBackStack(ScreenRoutes.HomeScreen.route, inclusive = false)
                        passwordGenerationViewModel.resetProcess()
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordManagerAppPreview() {
    PasswordManagerApp()
}