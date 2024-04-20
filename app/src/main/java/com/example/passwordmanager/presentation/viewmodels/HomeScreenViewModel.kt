package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.domain.usecases.GetAllPasswordsUseCase
import com.example.passwordmanager.presentation.states.HomeScreenUiState
import com.example.passwordmanager.presentation.states.PasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val passwords = getAllPasswordsUseCase.invoke()
                withContext(Dispatchers.Main) {
                    updatePasswords(passwords.map {
                        PasswordUiState(id = it.id, label = it.label, content = it.content, imagePath = it.imagePath)
                    })
                }
            }
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