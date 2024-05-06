package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.domain.models.Password
import com.example.passwordmanager.mappers.toEntity
import java.util.Date
import javax.inject.Inject

class SavePasswordUseCase @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    operator fun invoke(password: Password) {
        val date = Date(System.currentTimeMillis())
        var passwordEntityToBeSaved = password.toEntity()
        passwordEntityToBeSaved = passwordEntityToBeSaved.copy(createdAt = date)
        passwordDAO.addPassword(
            passwordEntityToBeSaved
        )
    }

}