package com.example.passwordmanager.domain.usecases

import com.example.passwordmanager.data.PasswordDAO
import com.example.passwordmanager.domain.models.Password
import com.example.passwordmanager.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetAllPasswordsUseCase @Inject constructor(
    private val passwordDAO: PasswordDAO
) {

    suspend fun invoke(): Flow<List<Password>> {
//        return passwordDAO.getAllPasswords().map {
//            Password(id = it.id, content = it.content, label = it.label, imagePath = it.imagePath)
//        }

        return passwordDAO.getAllPasswords().map { passwordsEntity ->
            passwordsEntity.map { it.toDomain() }
        }
    }
}