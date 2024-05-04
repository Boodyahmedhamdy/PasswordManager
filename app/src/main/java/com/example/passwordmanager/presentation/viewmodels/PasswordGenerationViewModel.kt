package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.domain.models.PasswordBuilder
import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration
import com.example.passwordmanager.domain.usecases.SavePasswordUseCase
import com.example.passwordmanager.mappers.toDomain
import com.example.passwordmanager.presentation.states.PasswordGenerationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// make it work with hilt
@HiltViewModel
class PasswordGenerationViewModel @Inject constructor(
    private val savePasswordUseCase: SavePasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(PasswordGenerationUiState())
    val state = _state.asStateFlow()


    fun savePassword() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                savePasswordUseCase(
                    state.value.passwordPreviewScreenUiState.password.toDomain()
                )
            }
        }
    }


    fun generatePassword(configuration: PasswordGenerationConfiguration) {
        val password = PasswordBuilder.build(configuration).content
        updateGeneratedPassword(password)
    }

    // updates ui -- both screens
    fun resetProcess() {
        _state.update {
            PasswordGenerationUiState()
        }
    }

    // updates ui -- preview screen
    fun updateGeneratedPasswordLabel(newLabel: String) {
        _state.update {
            it.copy(
                passwordPreviewScreenUiState = it.passwordPreviewScreenUiState.copy(
                    password = it.passwordPreviewScreenUiState.password.copy(
                        label = newLabel
                    )
                )
            )
        }
    }

    fun showAlertDialog() {
        updateShowAlertDialog(true)
    }
    fun hideAlertDialog() {
        updateShowAlertDialog(false)
    }
    private fun updateShowAlertDialog(newShow: Boolean) {
        _state.update {
            it.copy(
                passwordPreviewScreenUiState = it.passwordPreviewScreenUiState.copy(
                    showAlertDialog = newShow
                )
            )
        }
    }
    fun updateGeneratedPassword(newPassword: String) {
        _state.update {
            it.copy(
                passwordPreviewScreenUiState = it.passwordPreviewScreenUiState.copy(
                    password = it.passwordPreviewScreenUiState.password.copy(
                        content = newPassword
                    )
                )
            )
        }
    }

    // updates ui -- configuration screen
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