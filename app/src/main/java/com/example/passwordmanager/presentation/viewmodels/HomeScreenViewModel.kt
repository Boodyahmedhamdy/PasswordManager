package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.domain.usecases.GetAllPasswordsUseCase
import com.example.passwordmanager.mappers.toUiState
import com.example.passwordmanager.presentation.states.HomeScreenUiState
import com.example.passwordmanager.presentation.states.PasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllPasswordsUseCase: GetAllPasswordsUseCase
): ViewModel() {
    /**
    * show all passwords from database using repository
    * add new password
    * generate new password
    * */

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()


    init {
        watchPasswords()
    }

    fun watchPasswords() {
        viewModelScope.launch {
            updateIsLoading(true)
            getAllPasswordsUseCase.invoke().collect {
                updatePasswords(
                    it.map { password ->
                        password.toUiState()
                    }
                )
                println("passwords are ready")
                updateIsLoading(false)
            }
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }
    private fun updatePasswords(passwords: List<PasswordUiState>) {
        _state.update {
            it.copy(
                passwords = passwords
            )
        }
    }
}