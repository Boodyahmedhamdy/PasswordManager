package com.example.passwordmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PasswordDAO {

    @Query("SELECT * FROM PASSWORDS")
    fun getAllPasswords(): List<PasswordEntity>

    @Query("SELECT * FROM PASSWORDS WHERE id = :id")
    fun getPasswordById(id: Int): PasswordEntity?
    // list of passwords that like the passed string -- used in search feature
    @Query("SELECT * FROM PASSWORDS WHERE label LIKE :label")
    fun getPasswordByLabel(label: String): List<PasswordEntity>

    @Insert
    fun addPassword(passwordEntity: PasswordEntity)

    @Update()
    fun updatePassword(newPassword: PasswordEntity)
    @Delete
    fun deletePassword(passwordEntity: PasswordEntity)
    @Query("DELETE FROM PASSWORDS WHERE id = :id")
    fun deletePasswordById(id: Int)
}