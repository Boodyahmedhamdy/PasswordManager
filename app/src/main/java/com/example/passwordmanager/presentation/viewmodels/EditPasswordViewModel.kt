package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.domain.usecases.GetPasswordById
import com.example.passwordmanager.mappers.toUiState
import com.example.passwordmanager.presentation.states.EditPasswordUiState
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
class EditPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPasswordById: GetPasswordById
): ViewModel() {

    private val _state = MutableStateFlow(EditPasswordUiState())
    val state = _state.asStateFlow()

    private val passedId = checkNotNull(savedStateHandle["passwordId"])


    init {
        updatePasswordId(passedId as Int)
        viewModelScope.launch(Dispatchers.IO) {
            val password = getPasswordById(
                _state.value.editPasswordScreenUiState.password.id
            )
            withContext(Dispatchers.Main) {
                updatePassword(password?.toUiState() ?: PasswordUiState(content = "ERROR- PASSWORD WAS NULL"))
            }
        }
    }



    // ---------------------
    // UI UPDATES
    // ---------------------
    fun updatePassword(password: PasswordUiState) {
        _state.update {
            it.copy(
                editPasswordScreenUiState = it.editPasswordScreenUiState.copy(
                    password = password
                )
            )
        }
    }
    fun updatePasswordId(id: Int) {
        _state.update {
            it.copy(
                editPasswordScreenUiState = it.editPasswordScreenUiState.copy(
                    password = it.editPasswordScreenUiState.password.copy(
                        id = id
                    )
                )
            )
        }
    }




}