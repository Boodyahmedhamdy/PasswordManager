package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.presentation.states.PasswordUiState

@Composable
fun PasswordDetailsScreen(
    state: PasswordUiState,
    modifier: Modifier = Modifier,
    onEditButtonClicked: () -> Unit = {},
    onCopyButtonClicked: () -> Unit = {}
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // label
        Text(
            text = "${state.label} Password is",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Start
        )
        // content
        Text(
            text = state.content,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Column {

            // copy button
            Button(onClick = { onCopyButtonClicked() }) {
                Icon(imageVector = Icons.Outlined.ContentCopy, contentDescription = null)
                Text(text = "Copy to Clipboard")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // edit button
            OutlinedButton(onClick = { onEditButtonClicked() }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                Text(text = "edit this password")
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordDetailsScreenPreview() {
    PasswordDetailsScreen(
        state = PasswordUiState(content = "passwordpasswordpasswordpassword", label = "Facebook"),
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}