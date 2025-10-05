package com.mordva.security.internal

import com.mordva.security.external.KeyStoreRepository
import com.mordva.security.internal.util.SecurityException

internal class KeyStoreRepositoryImpl(
    private val storage: SecurityStorage,
    private val manager: KeyStoreCustomManager
) : KeyStoreRepository {
    override suspend fun encryptPassword(value: String): Result<Unit> {
        try {
            val encrypted = manager.encrypt(value)
            storage.save("password", encrypted)
        } catch (e: Exception) {
            return Result.failure(SecurityException(e.message))
        }

        return Result.success(Unit)
    }

    override suspend fun decryptPassword(): Result<String> {
        return try {
            val token = storage.get("password")
            val decryptedToken = manager.decrypt(token)
            Result.success(decryptedToken)
        } catch (e: Exception) {
            Result.failure(SecurityException(e.message))
        }
    }
}
