package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.domain.models.Password
import com.example.passwordmanager.mappers.toEntity
import javax.inject.Inject

class SavePasswordUseCase @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    operator fun invoke(password: Password) {
        passwordDAO.addPassword(
            password.toEntity()
        )
    }

}