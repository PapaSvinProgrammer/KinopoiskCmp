package com.mordva.util

expect class WeakRef<T : Any>(value: T) {
    fun get(): T?
}
