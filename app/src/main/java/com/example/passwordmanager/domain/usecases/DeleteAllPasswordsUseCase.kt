package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import javax.inject.Inject

class DeleteAllPasswordsUseCase @Inject constructor(
    private val passwordsDAO: PasswordDAO
) {
    suspend operator fun invoke() {
        passwordsDAO.deleteAllPasswords()
    }
}