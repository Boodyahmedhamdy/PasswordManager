package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.passwordmanager.presentation.states.PasswordPreviewScreenUiState

@Composable
fun PasswordPreviewScreen(
    state: PasswordPreviewScreenUiState,
    modifier: Modifier = Modifier,
    onClickSavePassword: (password: String) -> Unit = {},
    onClickReGenerate: () -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // password
        Text(text = "the Generated Password is", style = MaterialTheme.typography.labelLarge)

        Text(text = state.password, style = MaterialTheme.typography.headlineMedium)


        // row contains 2 buttons
            // save
            // regenerate
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // save button
            Button(onClick = {
                val password = state.password
                onClickSavePassword(password)
            }) {
                Text(text = "Save")
            }

            OutlinedButton(onClick = {
                onClickReGenerate()
            }) {
                Text(text = "ReGenerate")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordPreviewScreenPreview() {
    PasswordPreviewScreen(state = PasswordPreviewScreenUiState("thisdflksjdflksdfj"))
}
