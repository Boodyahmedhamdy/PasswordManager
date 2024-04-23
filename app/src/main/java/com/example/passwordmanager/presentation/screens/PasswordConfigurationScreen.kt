package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration
import com.example.passwordmanager.presentation.components.BooleanConfigurationOption
import com.example.passwordmanager.presentation.states.PasswordConfigurationScreenUiState

@Composable
fun PasswordConfigurationScreen(
    state: PasswordConfigurationScreenUiState,
    modifier: Modifier = Modifier,
    onClickGenerate: (configuration: PasswordGenerationConfiguration) -> Unit = {},
    onConfigurationLengthChanged: (newLength: String) -> Unit = {},
    onConfigurationHasSpecialCharactersChanged: (Boolean) -> Unit = {},
    onConfigurationHasNumbersChanged: (Boolean) -> Unit = {},
    onConfigurationCaseSensitiveChanged: (Boolean) -> Unit = {}
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextField(
            value = "${state.configuration.length}",
            onValueChange = {
                onConfigurationLengthChanged(it)
            },
            maxLines = 1,
            label = {
                Text(text = "password length")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )

        BooleanConfigurationOption(
            state = state.configuration.hasNumbers,
            label = "has Numbers",
            onCheckChanged = {
                onConfigurationHasNumbersChanged(it)
            }
        )

        BooleanConfigurationOption(
            state = state.configuration.caseSensitive,
            label = "Case Sensitive",
            onCheckChanged = {
                onConfigurationCaseSensitiveChanged(it)
            }
        )

        BooleanConfigurationOption(
            state = state.configuration.hasSpecialCharacters,
            label = "Special Characters",
            onCheckChanged = {
                onConfigurationHasSpecialCharactersChanged(it)
            }
        )

        Button(
            onClick = { onClickGenerate(state.configuration) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Generate")
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordConfigurationScreenPreview() {
    PasswordConfigurationScreen(
        state = PasswordConfigurationScreenUiState(
            configuration = PasswordGenerationConfiguration(
                caseSensitive = false
            )
        ),

        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
}