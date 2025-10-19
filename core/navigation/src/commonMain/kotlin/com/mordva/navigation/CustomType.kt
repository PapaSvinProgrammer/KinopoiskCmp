package com.mordva.navigation

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import com.eygraber.uri.UriCodec
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListTypePair = typeObject<List<Pair<String, String>>>()
    val MovieScreenType = typeObject<MovieScreenType>()
}

private inline fun <reified T> typeObject() = object : NavType<T>(
    isNullableAllowed = false
) {
    override fun put(bundle: SavedState, key: String, value: T) {
        bundle.write {
            putString(key, Json.encodeToString(value))
        }
    }

    override fun get(bundle: SavedState, key: String): T? {
        val res = bundle.read { getString(key) }
        return Json.decodeFromString(res)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun serializeAsValue(value: T): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}
