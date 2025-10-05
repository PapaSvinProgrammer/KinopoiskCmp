package com.mordva.security.internal

internal interface KeyStoreCustomManager {
    fun encrypt(data: String): String
    fun decrypt(data: String): String
}