package com.mordva.movie.utils

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import com.eygraber.uri.UriCodec
import com.mordva.movie.domain.model.PersonMovieScreenObject
import com.mordva.movie.domain.model.WatchabilityScreenObject
import kotlinx.serialization.json.Json

internal val WatchabilityType = object : NavType<WatchabilityScreenObject>(
    isNullableAllowed = false
) {
    override fun put(
        bundle: SavedState,
        key: String,
        value: WatchabilityScreenObject
    ) {
        bundle.write {
            putString(key, Json.encodeToString(value))
        }
    }

    override fun get(
        bundle: SavedState,
        key: String
    ): WatchabilityScreenObject? {
        val res = bundle.read { getString(key) }
        return Json.decodeFromString(res)
    }

    override fun parseValue(value: String): WatchabilityScreenObject {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun serializeAsValue(value: WatchabilityScreenObject): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}

internal val PersonMovieListScreenObjectType = object : NavType<List<PersonMovieScreenObject>>(
    isNullableAllowed = false
) {
    override fun put(
        bundle: SavedState,
        key: String,
        value: List<PersonMovieScreenObject>
    ) {
        bundle.write {
            putString(key, Json.encodeToString(value))
        }
    }

    override fun get(
        bundle: SavedState,
        key: String
    ): List<PersonMovieScreenObject>? {
        val res = bundle.read { getString(key) }
        return Json.decodeFromString(res)
    }

    override fun parseValue(value: String): List<PersonMovieScreenObject> {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun serializeAsValue(value: List<PersonMovieScreenObject>): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}