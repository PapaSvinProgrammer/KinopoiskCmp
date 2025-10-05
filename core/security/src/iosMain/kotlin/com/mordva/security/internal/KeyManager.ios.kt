package com.mordva.security.internal

class KeyManager : KeyStoreCustomManager {
    override fun encrypt(data: String): String {
        return data
    }

    override fun decrypt(data: String): String {
        return data
    }
}