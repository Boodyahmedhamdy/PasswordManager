package com.example.passwordmanager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.presentation.screens.HomeScreen
import com.example.passwordmanager.presentation.screens.PasswordConfigurationScreen
import com.example.passwordmanager.presentation.screens.PasswordDetailsScreen
import com.example.passwordmanager.presentation.screens.PasswordEditScreen
import com.example.passwordmanager.presentation.screens.PasswordPreviewScreen
import com.example.passwordmanager.presentation.screens.ScreenRoutes
import com.example.passwordmanager.presentation.screens.SecurityGateScreen
import com.example.passwordmanager.presentation.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerApp() {
    val viewmodel: HomeScreenViewModel = hiltViewModel()
    val state = viewmodel.state.collectAsState()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.home))
            }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO: add new password from viewmodel*/ }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add new password")
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    NavigationBarItem(
                        selected = true,
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Home"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Home"
                            )
                        }
                    )

            })
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeScreen.route
        ) {
            // home
            composable(ScreenRoutes.HomeScreen.route) {
                HomeScreen(
                    state = state.value,
                    onPasswordListItemClicked = { /*TODO*/ },
                    onPasswordListItemCopyClicked = { /*TODO*/ },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize())
            }
            // security
            composable(ScreenRoutes.SecurityGateScreen.route) {
                SecurityGateScreen()
            }
            // details
            composable(ScreenRoutes.PasswordDetailsScreen.route) {
                PasswordDetailsScreen()
            }
            // edit
            composable(ScreenRoutes.PasswordEditScreen.route) {
                PasswordEditScreen()
            }

            // config
            composable(ScreenRoutes.PasswordConfigurationScreen.route) {
                PasswordConfigurationScreen()
            }

            // preview
            composable(ScreenRoutes.PasswordPreviewScreen.route) {
                PasswordPreviewScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordManagerAppPreview() {
    PasswordManagerApp()
}