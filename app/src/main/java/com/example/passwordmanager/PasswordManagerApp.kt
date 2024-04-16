package com.example.passwordmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.passwordmanager.presentation.screens.HomeScreen
import com.example.passwordmanager.presentation.states.HomeScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerApp() {

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

    ) {
        HomeScreen(
            state = HomeScreenUiState(),
            onPasswordListItemClicked = { /*TODO*/ },
            onPasswordListItemCopyClicked = { /*TODO*/ },
            modifier = Modifier
                .padding(it)
                .fillMaxSize())
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordManagerAppPreview() {
    PasswordManagerApp()
}