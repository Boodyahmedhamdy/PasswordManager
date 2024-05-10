package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.passwordmanager.R
import com.example.passwordmanager.presentation.components.PasswordListItem
import com.example.passwordmanager.presentation.states.HomeScreenUiState

@Composable
fun HomeScreen(
    state: HomeScreenUiState,
    onPasswordListItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    if(state.isLoading){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else if (state.passwords.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = modifier) {
                Image(painter = painterResource(id = R.drawable.lock), contentDescription = null)
                Text(
                    text = "you don't have any passwords yet, you can create new one using + Button below",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
    else {
        LazyColumn(modifier = modifier) {
            items(state.passwords) {
                PasswordListItem(
                    it,
                    onClick = { onPasswordListItemClicked(it.id) },
                )
            }
        }
    }
}