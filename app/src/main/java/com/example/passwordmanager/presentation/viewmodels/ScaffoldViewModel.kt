package com.example.passwordmanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.domain.usecases.DeleteAllPasswordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val deleteAllPasswordsUseCase: DeleteAllPasswordsUseCase
): ViewModel() {

    fun deleteAllPasswords() {
        viewModelScope.launch {
            deleteAllPasswordsUseCase()
        }
    }


}