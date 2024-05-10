package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.domain.models.Password
import com.example.passwordmanager.mappers.toDomain
import javax.inject.Inject

class GetPasswordById @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    operator fun invoke(id: Int): Password? {
        val passwordEntity = passwordDAO.getPasswordById(id)
        return passwordEntity?.toDomain()

    }
}