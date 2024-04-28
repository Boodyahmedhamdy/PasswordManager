package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.passwordmanager.domain.models.PasswordBuilder
import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration
import com.example.passwordmanager.presentation.states.PasswordGenerationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// make it work with hilt
class PasswordGenerationViewModel: ViewModel() {

    private val _state = MutableStateFlow(PasswordGenerationUiState())
    val state = _state.asStateFlow()


    fun generatePassword(configuration: PasswordGenerationConfiguration) {
        val password = PasswordBuilder.build(configuration).content
        updateGeneratedPassword(password)
    }

    fun updateGeneratedPassword(newPassword: String) {
        _state.update {
            it.copy(
                passwordPreviewScreenUiState = it.passwordPreviewScreenUiState.copy(
                    password = newPassword
                )
            )
        }
    }

    // configuration updates
    fun updateLength(newLength: Int) {

        _state.update {
            it.copy(
                passwordConfigurationScreenUiState =
                it.passwordConfigurationScreenUiState.copy(
                    configuration =
                    it.passwordConfigurationScreenUiState.configuration.copy(length = newLength)
                ),
            )
        }
    }
    fun updateHasNumbers(newHasNumbers: Boolean) {
        _state.update {
            it.copy(
                passwordConfigurationScreenUiState =
                it.passwordConfigurationScreenUiState.copy(
                    configuration =
                    it.passwordConfigurationScreenUiState.configuration.copy(hasNumbers = newHasNumbers)
                ),
            )
        }
    }
    fun updateSpecialCharacters(newSpecialCharacters: Boolean) {
        _state.update {
            it.copy(
                passwordConfigurationScreenUiState =
                it.passwordConfigurationScreenUiState.copy(
                    configuration =
                    it.passwordConfigurationScreenUiState.configuration.copy(
                        hasSpecialCharacters = newSpecialCharacters
                    )
                ),
            )
        }
    }
    fun updateCaseSensitive(newCaseSensitive: Boolean) {
        _state.update {
            it.copy(
                passwordConfigurationScreenUiState =
                it.passwordConfigurationScreenUiState.copy(
                    configuration =
                    it.passwordConfigurationScreenUiState.configuration.copy(caseSensitive = newCaseSensitive)
                ),
            )
        }
    }
}