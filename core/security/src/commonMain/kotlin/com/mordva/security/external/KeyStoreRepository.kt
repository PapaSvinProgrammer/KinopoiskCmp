package com.mordva.security.external

interface KeyStoreRepository {
    suspend fun encryptPassword(value: String): Result<Unit>
    suspend fun decryptPassword(): Result<String>
}
