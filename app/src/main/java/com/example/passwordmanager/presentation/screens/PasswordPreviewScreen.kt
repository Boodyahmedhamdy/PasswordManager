package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.passwordmanager.presentation.states.PasswordPreviewScreenUiState

@Composable
fun PasswordPreviewScreen(
    state: PasswordPreviewScreenUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.password, style = MaterialTheme.typography.headlineMedium)
    }
}
