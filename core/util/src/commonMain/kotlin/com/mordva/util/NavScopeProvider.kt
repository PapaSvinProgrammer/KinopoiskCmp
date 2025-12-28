package com.mordva.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavBackStackEntry
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun rememberNavScope(
    entry: NavBackStackEntry,
    qualifier: Qualifier
): Scope {
    val scopeId = entry.id
    val koin = getKoin()
    val lifecycle = entry.lifecycle

    // создать scope один раз
    val scope = remember(scopeId) {
        koin.getOrCreateScope(
            scopeId = scopeId,
            qualifier = qualifier
        )
    }

    // закрыть scope при уничтожении backStackEntry
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                koin.getScopeOrNull(scopeId)?.close()
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return scope
}
