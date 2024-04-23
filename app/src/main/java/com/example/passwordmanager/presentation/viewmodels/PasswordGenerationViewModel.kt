package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.passwordmanager.domain.models.PasswordGenerationConfiguration
import com.example.passwordmanager.presentation.states.PasswordConfigurationScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// make it work with hilt
class PasswordGenerationViewModel: ViewModel() {

    private val _state = MutableStateFlow(PasswordConfigurationScreenUiState())
    val state = _state.asStateFlow()


    // configuration updates
    fun updateLength(newLength: Int) {

        _state.update {
            it.copy(
                configuration = it.configuration.copy(length = newLength)
            )
        }
    }
    fun updateHasNumbers(newHasNumbers: Boolean) {
        _state.update {
            it.copy(
                configuration = it.configuration.copy(
                    hasNumbers = newHasNumbers
                )
            )
        }
    }
    fun updateSpecialCharacters(newSpecialCharacters: Boolean) {
        _state.update {
            it.copy(
                configuration = it.configuration.copy(
                    hasSpecialCharacters = newSpecialCharacters
                )
            )
        }
    }
    fun updateCaseSensitive(newCaseSensitive: Boolean) {
        _state.update {
            it.copy(
                configuration = it.configuration.copy(
                    caseSensitive = newCaseSensitive
                )
            )
        }
    }



}