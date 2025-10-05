package com.mordva.util

import platform.Foundation.NSLog
import platform.Foundation.NSString

actual object Log {
    actual fun d(tag: String, message: String) {
        NSLog(
            "DEBUG: %@ - %@",
            tag.asNSString(),
            message.asNSString()
        )
    }

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        NSLog(
            "ERROR: %@ - %@ %@",
            tag.asNSString(),
            message.asNSString(),
            (throwable?.message ?: "").asNSString()
        )
    }
}

fun String.asNSString(): NSString = this as NSString