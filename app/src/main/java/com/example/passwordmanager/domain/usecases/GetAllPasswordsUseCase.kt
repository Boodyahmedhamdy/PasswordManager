package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.domain.models.Password
import javax.inject.Inject


class GetAllPasswordsUseCase @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    fun invoke(): List<Password> {
        return passwordDAO.getAllPasswords().map {
            Password(id = it.id, content = it.content, label = it.label, imagePath = it.imagePath)
        }
    }
}