package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.data.PasswordEntity
import javax.inject.Inject
import kotlin.random.Random

class SavePasswordUseCase @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    operator fun invoke(password: String) {
        val randomID = Random.nextInt(1000)
        passwordDAO.addPassword(
            PasswordEntity(
                label = "generatedPassword-${randomID}",
                content = password
            )
        )
    }

}