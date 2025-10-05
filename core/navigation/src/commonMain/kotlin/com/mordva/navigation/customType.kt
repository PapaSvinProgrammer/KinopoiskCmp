package com.mordva.navigation

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import com.eygraber.uri.UriCodec
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListTypePair = object : NavType<List<Pair<String, String>>>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: List<Pair<String, String>>) {
            bundle.write {
                putString(key, Json.encodeToString(value))
            }
        }

        override fun get(bundle: SavedState, key: String): List<Pair<String, String>>? {
            val res = bundle.read { getString(key) }
            return Json.decodeFromString(res)
        }

        override fun parseValue(value: String): List<Pair<String, String>> {
            return Json.decodeFromString(UriCodec.decode(value))
        }

        override fun serializeAsValue(value: List<Pair<String, String>>): String {
            return UriCodec.encode(Json.encodeToString(value))
        }
    }
}