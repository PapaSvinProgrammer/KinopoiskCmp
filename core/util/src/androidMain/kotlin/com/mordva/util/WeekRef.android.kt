package com.mordva.util

import java.lang.ref.WeakReference

actual class WeakRef<T : Any> actual constructor(value: T) {
    private val ref = WeakReference(value)
    actual fun get(): T? = ref.get()
}