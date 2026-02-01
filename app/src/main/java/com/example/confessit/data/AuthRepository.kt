package com.example.confessit.data

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    fun logout()
    fun isUserLoggedIn(): Boolean
}